/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.gonet.iso.core.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.iso.config.Properties;
import us.gonet.iso.core.rest.TCPNode;
import us.gonet.iso.core.socket.ClientController;
import us.gonet.iso.core.timer.TimerTable;
import us.gonet.iso8583.constants.ReversalCodes;
import us.gonet.iso8583.message.Request0800;
import us.gonet.iso8583.message.Request0810;
import us.gonet.iso8583.message.Reversal0420;
import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.NodeProsa;
import us.gonet.utils.DecodeISO8583;
import us.gonet.utils.Utilities;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static us.gonet.iso8583.constants.MessageTypes.NETWORK_MANAGEMENT_REQUEST;
import static us.gonet.iso8583.constants.NetworkManagement.LOGOFF;
import static us.gonet.iso8583.constants.NetworkManagement.LOGON;

@Component ( "MessageController" )
public class MessageController {

    @Autowired
    ClientController clientController;

    @Autowired
    MessageHandler messageHandler;

    @Autowired
    TCPNode tcpNode;

    @Autowired
    Properties props;

    private EchoTask echoTask;
    private static final Logger LOG = LoggerFactory.getLogger( MessageController.class );
    private static final Map < Integer, TimerTable > TIMER_TABLE_NMM = new LinkedHashMap <>();
    private static final Map < Integer, ISO > TIMER_TABLE_RESPONSE_NMM = new LinkedHashMap <>();
    private static final Map < Integer, TimerTable > TIMER_TABLE_ATM = new LinkedHashMap <>();
    private static final Map < Integer, ISO > TIMER_TABLE_RESPONSE_ATM = new LinkedHashMap <>();
    private static final Map < Integer, Object > LOCK_OBJECT = new LinkedHashMap <>();
    private static final Map < Integer, Object > LOCK_OBJECT_RED = new LinkedHashMap <>();
    private boolean echoStatus;


    ISO sendNNM ( ISO iso ) {
        TimerTable timerTable = new TimerTable( iso, 10, this );
        Integer key = Integer.parseInt( iso.getDataElements().get( 10 ).getContentField() );
        LOCK_OBJECT_RED.put( key, new Object() );
        TIMER_TABLE_NMM.put( key, timerTable );
        if ( isRunning() ) {
            clientController.sendMessage( Utilities.byteArrayToString( iso.getMessage().getBytes() ) );
            try {
                synchronized ( LOCK_OBJECT_RED.get( key ) ) {
                    lockMessage( key );
                    boolean flag = true;
                    while ( flag ) {
                        LOCK_OBJECT_RED.get( key ).wait();
                        flag = false;
                    }
                }
                unlockMessage( key );
                LOCK_OBJECT_RED.remove( key );
                ISO response = TIMER_TABLE_RESPONSE_NMM.get( key );
                TIMER_TABLE_RESPONSE_NMM.remove( key );
                if ( response != null ) {
                    iso = response;
                } else {
                    iso = new ISO();
                    iso.setTimeOut( true );
                }
            } catch ( InterruptedException ex ) {
                LOG.error( "Error waiting for message" );
                Utilities.interrupt();

            }
        }
        return iso;
    }

    ISO sendATM ( ISO iso ) {
        TimerTable timerTable = new TimerTable( iso, 90, this );
        Integer key = Integer.parseInt( iso.getDataElements().get( 10 ).getContentField() );
        TIMER_TABLE_ATM.put( key, timerTable );
        LOCK_OBJECT.put( key, new Object() );
        if ( isRunning() ) {
            clientController.sendMessage( Utilities.byteArrayToString( iso.getMessage().getBytes() ) );
            try {
                synchronized ( LOCK_OBJECT.get( key ) ) {
                    lockMessage( key );
                    boolean flag = false;
                    while ( ! flag ) {
                        LOCK_OBJECT.get( key ).wait();
                        flag = true;
                    }
                }
                unlockMessage( key );
                LOCK_OBJECT.remove( key );
                ISO response = TIMER_TABLE_RESPONSE_ATM.get( key );
                TIMER_TABLE_RESPONSE_ATM.remove( key );
                if ( response != null ) {
                    iso = response;
                } else {

                    iso = new ISO();
                    iso.setTimeOut( true );
                }
            } catch ( InterruptedException ex ) {
                LOG.error( "Error sendAtm" );
                Utilities.interrupt();


            }
        }
        return iso;
    }

    public void receive ( String message ) {
        DecodeISO8583 response = new DecodeISO8583( message );
        if ( "00".equals( response.getHEADER().get( 1 ).getContentField() ) ) {
            if ( !echoStatus ) {
                if ( isNetworkRequest( response.getIso() ) ) {
                    if ( isLogon( response.getIso() ) ) {
                        messageHandler.setLogonFlag( true );
                    } else if ( isLogoff( response.getIso() ) ) {
                        messageHandler.setLogonFlag( false );
                    }
                    Request0810 request0810 = new Request0810( response.getIso() );
                    clientController.
                            sendMessage( Utilities.byteArrayToString( request0810.getIso().getMessage().getBytes() ) );
                } else {
                    receiveNMM( response.getIso() );
                }
            }else {
                receiveNMM( response.getIso() );
            }
        } else if ( "01".equals( response.getHEADER().get( 1 ).getContentField() ) ) {
            receiveATM( response.getIso() );
        }
    }

    private void receiveNMM ( ISO response ) {
        Integer key = Integer.parseInt( response.getDataElements().get( 10 ).getContentField() );
        TimerTable timerTable = TIMER_TABLE_NMM.get( key );
        if ( timerTable != null ) {
            timerTable.getTimer().stopTask();
            TIMER_TABLE_NMM.remove( key );
            TIMER_TABLE_RESPONSE_NMM.put( key, response );
            if ( LOCK_OBJECT_RED.get( key ) != null ) {
                synchronized ( LOCK_OBJECT_RED.get( key ) ) {
                    LOCK_OBJECT_RED.get( key ).notifyAll();
                }
            }
        }
    }

    private void receiveATM ( ISO response ) {
        Integer key = Integer.parseInt( response.getDataElements().get( 10 ).getContentField() );
        TimerTable timerTable = TIMER_TABLE_ATM.get( key );
        if ( timerTable != null ) {
            timerTable.getTimer().stopTask();
            TIMER_TABLE_ATM.remove( key );
            TIMER_TABLE_RESPONSE_ATM.put( key, response );
            if ( LOCK_OBJECT.get( key ) != null ) {
                synchronized ( LOCK_OBJECT.get( key ) ) {
                    LOCK_OBJECT.get( key ).notifyAll();
                }
            }
        } else {
            if ( LOG.isWarnEnabled() ){
                LOG.warn( String.format( "Message received to late: %s", response.getMessage() ) );
            }
            if ( response.getDataElements().get( 38 ).getContentField().equals( "00" ) && response.getHeader().get( 6 ).getContentField().equals( "0210" ) ) {
                Reversal0420 rev0420 = new Reversal0420( response, ReversalCodes.RESPONSE_RECEIVED_TOO_LATE );
                MessageController mc = new MessageController();
                if ( LOG.isInfoEnabled() ){
                    LOG.info( "Reversal" );
                }
                mc.sendATM( rev0420.getIso() );
            }
        }
    }

    public void notifyTimeOut ( ISO response ) {
        Integer key = Integer.parseInt( response.getDataElements().get( 10 ).getContentField() );
        if ( response.getHeader().get( 1 ).getContentField().equals( "01" ) ){
            if ( LOCK_OBJECT.get( key ) == null ) {
                return;
            }
            synchronized ( LOCK_OBJECT.get( key ) ) {
                LOCK_OBJECT.get( key ).notifyAll();
                TIMER_TABLE_ATM.remove( key );
            }
        }else{
            if ( LOCK_OBJECT_RED.get( key ) == null ) {
                return;
            }
            synchronized ( LOCK_OBJECT_RED.get( key ) ) {
                LOCK_OBJECT_RED.get( key ).notifyAll();
            }
        }

    }

    boolean isRunning () {
        return clientController.isRunning();
    }

    boolean turnOnSocket () {
        NodeProsa nodeProsa;
        try {
            nodeProsa = tcpNode.getNodeTCP( props.getNodeName() );
        } catch ( ServerException e ) {
            LOG.error( "Error turnOnSocket" );
            return false;
        }
        return clientController.turnOnSocket( nodeProsa.getIp(), Integer.parseInt( nodeProsa.getPort() ), 10000 );
    }

    boolean turnOffSocket () {
        return clientController.turnOffSocket();
    }

    private boolean isNetworkRequest ( ISO message ) {
        String messageType = message.getHeader().get( 6 ).getContentField();
        return messageType.equals( NETWORK_MANAGEMENT_REQUEST.getValue() );
    }

    private boolean isLogon ( ISO message ) {
        String messageType = message.getDataElements().get( 69 ).getContentField();
        return messageType.equals( LOGON.getValue() );
    }

    private boolean isLogoff ( ISO message ) {
        String messageType = message.getDataElements().get( 69 ).getContentField();
        return messageType.equals( LOGOFF.getValue() );
    }

    private void lockMessage ( Integer key ) {
        if ( LOG.isDebugEnabled() ) {
            String thReadName = Thread.currentThread().getName();
            LOG.debug( String.format( "Lock message with key: %d in thread %s", key, thReadName ) );
        }
    }

    private void unlockMessage ( Integer key ) {
        if ( LOG.isDebugEnabled() ) {
            String thReadName = Thread.currentThread().getName();
            LOG.debug( String.format( "UnLock message with key: %d in thread %s", key, thReadName ) );
        }
    }

    public void startEchoTask(){
        echoTask = new EchoTask();
        echoTask.start();
    }

    public void stopEchoTask(){
        if ( echoStatus ){
            echoTask.stopTask();
        }
        echoTask = null;
    }



    class EchoTask {

        private Timer timer = new Timer();
        private int i = 200000;

        void start () {
            echoStatus = true;
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run () {
                    if ( isRunning() ) {
                        Map< String, String > dataElements = new LinkedHashMap<>();
                        dataElements.put( "p11", "" + i++ );
                        dataElements.put( "s70", "Echo-test" );
                        Request0800 rq0800 = new Request0800( dataElements );
                        ISO iso = sendNNM( rq0800.getIso() );
                        if ( iso.isTimeOut() ) {
                            stopTask();
                        }
                    } else {
                        LOG.error( "Se perdio la conexion con el HOST" );
                        if ( resetConnection() ) {
                            timer.cancel();
                            timer.purge();
                        }
                    }
                }
            };
            timer.scheduleAtFixedRate( timerTask, 0, 3000 );
        }

        void stopTask () {
            echoStatus = false;
            timer.cancel();
            timer.purge();
            timer = new Timer();
        }

        boolean resetConnection(){
            NodeProsa nodeProsa;
            try {
                nodeProsa = tcpNode.getNodeTCP( props.getNodeName() );
            } catch ( ServerException e ) {
                LOG.error( "Error resetConnection" );
                return false;
            }
            return clientController.resetConnection( nodeProsa.getIp(), Integer.parseInt( nodeProsa.getPort() ), 10000 );
        }
    }
}
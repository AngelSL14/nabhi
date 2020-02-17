package com.dksits.jke.core.handler;

import com.dksits.jke.config.Properties;
import com.dksits.jke.core.message.KeyExchangeResponse;
import com.dksits.jke.core.message.Request;
import com.dksits.jke.core.message.Response;
import com.dksits.jke.core.message.TranslatePinBlockResponse;
import com.dksits.jke.core.rest.TCPNode;
import com.dksits.jke.core.socket.ClientController;
import com.dksits.jke.core.timer.TimerTable;
import com.dksits.jke.model.NodeProsa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.utils.Utilities;

import java.util.LinkedHashMap;
import java.util.Map;


@Component( "MessageController" )
public class MessageController{

    @Autowired
    ClientController clientController;

    @Autowired
    TCPNode tcpNode;

    @Autowired
    Properties props;

    private static final Logger LOG = LoggerFactory.getLogger( MessageController.class );
    private static final Map< Integer, Object > LOCK_OBJECT = new LinkedHashMap<>();
    private static final Map< Integer, TimerTable > TIMER_TABLE_ATM = new LinkedHashMap<>();
    private static final Map< Integer, Response > TIMER_TABLE_RESPONSE_ATM = new LinkedHashMap<>();

    public Response send ( Request request ) {
        Response response = null;
        TimerTable timerTable = new TimerTable( request, 10, this );
        Integer key = Integer.parseInt( request.getSequence() );
        LOCK_OBJECT.put( key, new Object() );
        TIMER_TABLE_ATM.put( key, timerTable );
        if ( isRunning() ){
            clientController.sendMessage( request.toString() );
            try {
                synchronized( LOCK_OBJECT.get( key ) ){
                    lockMessage( key );
                    boolean flag = true;
                    while ( flag ){
                        LOCK_OBJECT.get( key ).wait();
                        flag = false;
                    }
                }
                unlockMessage( key );
                LOCK_OBJECT.remove( key );
                response = TIMER_TABLE_RESPONSE_ATM.get( key );
            } catch ( InterruptedException e ) {
                LOG.error( "Error waiting for message" );
                Utilities.interrupt();

            }
        }
        return response;
    }

    public void receive( String message ){
        Response response = new Response() {
            @Override
            public String getSequence() {
                return null;
            }

            @Override
            public String getMODE() {
                return null;
            }

            @Override
            public String getBLOCK() {
                return null;
            }

            @Override
            public void setError ( String error ) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        if ( message.startsWith( "1A" ) ){
            response = new KeyExchangeResponse.KeyResponseBuilder().build( message );
        }else if ( message.startsWith( "1B" )  ){
            response = new TranslatePinBlockResponse.TranslatePinBlockBuilder().build( message );
        } 
        Integer key = Integer.parseInt( response.getSequence() );
        TimerTable timerTable = TIMER_TABLE_ATM.get( key );
        if ( timerTable != null ){
            timerTable.getTimer().stopTask();
            TIMER_TABLE_ATM.remove( key );
            TIMER_TABLE_RESPONSE_ATM.put( key, response );
            if ( LOCK_OBJECT.get( key ) != null ){
                synchronized( LOCK_OBJECT.get( key ) ) {
                    LOCK_OBJECT.get(key).notifyAll();
                }
            }
        }
       
    }
    
    public void notifyTimeOut( Request request ){
        Integer key = Integer.parseInt( request.getSequence() );
        if ( LOCK_OBJECT.get( key ) == null ){
            return;
        }
        synchronized( LOCK_OBJECT.get( key ) ){
            LOCK_OBJECT.get( key ).notifyAll();
            LOCK_OBJECT.remove( key );
        }
    }
    
    public boolean isRunning(){
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
    
    public boolean turnOffSocket(){
        return clientController.turnOffSocket();
    }

    private void lockMessage( Integer key ){
        if ( LOG.isDebugEnabled() ){
            String thReadName = Thread.currentThread().getName();
            LOG.debug( String.format("Lock message with key: %d in thread %s", key, thReadName ) );
        }
    }

    private void unlockMessage( Integer key ){
        if ( LOG.isDebugEnabled() ){
            String thReadName = Thread.currentThread().getName();
            LOG.debug( String.format("UnLock message with key: %d in thread %s", key, thReadName ) );
        }
    }
}


package us.gonet.iso.core.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.iso.core.handler.MessageController;
import us.gonet.utils.ITCPController;
import us.gonet.utils.Utilities;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Component ( "ClientController" )
public class ClientController implements ITCPController {

    @Autowired
    Client client;

    @Autowired
    MessageController messageController;

    private static final Logger LOG = LoggerFactory.getLogger( ClientController.class );
    private DataInputStream in;
    private DataOutputStream out;

    private void starIOStream(){
        try{
            in = new DataInputStream( client.getSocket().getInputStream() );
            out = new DataOutputStream( client.getSocket().getOutputStream() );
        }catch ( IOException ex ){
            LOG.error( "Error Client Controller" );
        }
    }

    private void stopIOStream(){
        try {
            in.close();
            out.close();
        } catch ( IOException ex ) {
            LOG.error( " Error on stopIOStream " );
        }
    }

    private void run () {
        Utilities.runTCPController( this );
    }

    @Override
    public void receiveMessageUTF(){
        while ( client.isRunning() ){
            try {
                messageController.receive( in.readUTF() );
            } catch ( IOException ex ) {
                client.setRunning();
                if ( LOG.isDebugEnabled() ){
                    LOG.debug( "The connection to the host has been lost" );
                }
            }
        }
    }

    public void sendMessage( String message ){
        try {
            if ( client.isRunning() ){
                String str = "Me say: " + message;
                if ( LOG.isDebugEnabled() ){
                    LOG.debug( str );
                }
                out.writeUTF( message );
            }
        } catch (IOException ex) {
            LOG.error( " Error on sendMessage " );
        }
    }

    public boolean isRunning(){
        return client.isRunning();
    }

    public boolean resetConnection( String ipAddress, int port, int timeOut ){
        return client.resetConnection( ipAddress, port, timeOut );
    }

    public boolean turnOnSocket ( String ipAddress, int port, int timeOut ) {
        boolean turnOn = client.turnOn( ipAddress, port, timeOut );
        if ( turnOn ) {
            starIOStream();
            run();
        }
        return turnOn;
    }

    public boolean turnOffSocket () {
        boolean turnOff = client.turnOff();
        stopIOStream();
        if ( LOG.isInfoEnabled() ){
            LOG.info( "Stop Socket TCP" );
        }
        return turnOff;
    }
}
 
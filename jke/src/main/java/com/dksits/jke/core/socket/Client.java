package com.dksits.jke.core.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.gonet.utils.Utilities;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

@Component ( "client" )
public class Client{

    private Socket socket;
    private boolean running = false;
    private int count = 0;
    private static final int RETRY = 3;
    private static final Logger LOG = LoggerFactory.getLogger( Client.class );

    private boolean initClient( String ipAddress, int port, int timeOut ){
        SocketAddress socketAddress = new InetSocketAddress( ipAddress, port );
        socket = new Socket();
        try{
            socket.connect( socketAddress, timeOut );
            count = 0;
            running = true;
            return true;
        }catch( IOException e ){
            while ( count < RETRY ){
                Utilities.delay( 5 );
                count ++;
                if ( LOG.isWarnEnabled() ){
                    LOG.warn( "Retrying connection" );
                }
                running = initClient( ipAddress, port, timeOut );
            }
        }
        return false;
    }

    boolean turnOn( String ipAddress, int port, int timeOut ){
        return initClient( ipAddress, port, timeOut );
    }

    boolean turnOff(){
        try {
            running = false;
            socket.close();
            return true;
        } catch ( IOException ex ) {
            LOG.error( "Error on turnOff" );
            return false;
        }
    }

    Socket getSocket(){
        return socket;
    }

    boolean isRunning(){
        return running;
    }

    void setRunning(){
        this.running = false;
    }

    boolean resetConnection( String ipAddress, int port, int timeOut ){
        if ( running ){
            count = 0;
            return initClient( ipAddress, port, timeOut );
        }
        return false;
    }
}

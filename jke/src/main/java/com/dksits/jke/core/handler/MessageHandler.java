package com.dksits.jke.core.handler;

import com.dksits.jke.core.message.Request;
import com.dksits.jke.core.message.Response;
import com.dksits.jke.exceptionutils.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component( "messageHandler" )
public class MessageHandler {
    
    @Autowired
    MessageController mc;

    public Response sendMessage( Request request ){
        return mc.send( request );
    }

    public String turnOnSocket(){
        if ( mc.isRunning() ) {
            return Errors.SOCKET_ALREADY_ON.getValue();
        } else {
            if ( mc.turnOnSocket() ) {
                return Errors.SOCKET_OPEN.getValue();
            } else {
                return Errors.SOCKET_OPEN_ERROR.getValue();
            }
        }
    }

    public String turnOffSocket () {
        if ( mc.isRunning() ) {
            if ( mc.turnOffSocket() ) {
                return Errors.SOCKET_CLOSE.getValue();
            } else {
                return Errors.SOCKET_CLOSE_ERROR.getValue();
            }
        } else {
            return Errors.SOCKET_ALREADY_CLOSED.getValue();
        }
    }
    
    public boolean status(){
        return mc.isRunning();
    }

}

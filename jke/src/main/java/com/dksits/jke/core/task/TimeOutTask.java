/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dksits.jke.core.task;

import com.dksits.jke.core.handler.MessageController;
import com.dksits.jke.core.message.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Timer;
import java.util.TimerTask;


public class TimeOutTask {


    private int second;
    private final Timer timer;
    private int key;
    private Request request;
    private static final Logger LOG = LoggerFactory.getLogger( TimeOutTask.class );
    private MessageController messageController;

    private final TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if ( second == 0 ){
                if ( LOG.isDebugEnabled() ){
                    LOG.debug( String.format( "Timeout key: %d", key ) );
                }
                stopTask();
                messageController.notifyTimeOut( request );
            }else{
                second --;
            }
        }
    };

    public TimeOutTask( Request request, int seconds, MessageController messageController ){
        this.request = request;
        this.second = seconds;
        this.messageController = messageController;
        this.key = Integer.parseInt( request.getSequence() );
        timer = new Timer();
    }

    public void start() {
        timer.schedule( task, 0, 1000 );
    }

    public void stopTask(){
        timer.cancel();
        timer.purge();
    }
}

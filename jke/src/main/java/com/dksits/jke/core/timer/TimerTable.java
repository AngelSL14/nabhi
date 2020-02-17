package com.dksits.jke.core.timer;

import com.dksits.jke.core.handler.MessageController;
import com.dksits.jke.core.message.Request;
import com.dksits.jke.core.task.TimeOutTask;

public class TimerTable {


    private final Request request;
    private final TimeOutTask timer;
    
    public TimerTable( Request request, int seconds, MessageController messageController ){
        this.request = request;
        timer = new TimeOutTask( this.request, seconds, messageController );
        timer.start();
    }
    
    public Request getRequest() {
        return request;
    }
    
    public TimeOutTask getTimer() {
        return timer;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package us.gonet.iso.core.timer;

import us.gonet.iso.core.handler.MessageController;
import us.gonet.iso.core.task.TimeOutTask;
import us.gonet.serializable.data.ISO;

public class TimerTable {

    private final ISO iso;
    private final TimeOutTask timer;

    public TimerTable ( ISO iso, int seconds, MessageController messageController) {
        this.iso = iso;
        timer = new TimeOutTask( iso, seconds, messageController );
        timer.start();
    }

    public ISO getIso () {
        return iso;
    }

    public TimeOutTask getTimer () {
        return timer;
    }

}

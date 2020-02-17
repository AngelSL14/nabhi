package us.gonet.iso.core.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.gonet.iso.core.handler.MessageController;
import us.gonet.serializable.data.ISO;

import java.util.Timer;
import java.util.TimerTask;

public class TimeOutTask {

    private MessageController messageController;

    private int second;
    private final Timer timer;
    private int key;
    private ISO iso;
    private static final Logger LOG = LoggerFactory.getLogger( TimeOutTask.class );
    private final TimerTask task = new TimerTask() {
        @Override
        public void run () {
            if ( second == 0 ) {
                if ( LOG.isDebugEnabled() ) {
                    LOG.debug( String.format( "Timeout key: %d", key ) );
                }
                stopTask();
                messageController.notifyTimeOut( iso );
            } else {
                second--;
            }
        }
    };

    public TimeOutTask ( ISO iso, int seconds, MessageController messageController ) {
        this.iso = iso;
        this.second = seconds;
        this.messageController = messageController;
        this.key = Integer.parseInt( iso.getDataElements().get( 10 ).getContentField() );
        timer = new Timer();
    }

    public void start () {
        timer.schedule( task, 0, 1000 );
    }

    public void stopTask () {
        timer.cancel();
        timer.purge();
    }

}

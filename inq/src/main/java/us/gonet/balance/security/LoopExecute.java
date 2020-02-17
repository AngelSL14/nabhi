package us.gonet.balance.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.balance.config.Properties;
import us.gonet.security.auth.AuthIn;
import us.gonet.security.model.ServiceAllowed;
import us.gonet.security.rest.model.User;

import java.util.Timer;
import java.util.TimerTask;

@Component( "loopExecute" )
public class LoopExecute {

    @Autowired
    AuthIn authIn;

    @Autowired
    Properties props;

    public void refreshToken(){
        Timer timer = new Timer("Token");
        User userIso = new User();
        userIso.setId( props.getIsoTknUser() );
        userIso.setP ( props.getIsoTknPwd () );
        User userJke = new User();
        userJke.setId( props.getJkeTknUser() );
        userJke.setP ( props.getJkeTknPwd() );
        User userRcpt = new User();
        userRcpt.setId( props.getRcptTknUser() );
        userRcpt.setP ( props.getRcptTknPwd() );
        User userJdb = new User();
        userJdb.setId( props.getJdbTknUser() );
        userJdb.setP ( props.getJdbTknPwd() );
        TimerTask repeatTask = new TimerTask() {
            @Override
            public void run() {
                authIn.startAuthServices(
                        new ServiceAllowed( userJdb,"JDB", true ),
                        new ServiceAllowed( userIso,"ISO", true ),
                        new ServiceAllowed( userJke,"JKE", true ),
                        new ServiceAllowed( userRcpt,"RCPT", false ) );
            }
        };
        long delay = 1000L;
        long period = 1000L * 60L * 60L * 24 * 2L;
        timer.scheduleAtFixedRate( repeatTask,delay,period );
    }
}

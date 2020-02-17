package us.gonet.iso.core.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.iso.core.utils.Errors;
import us.gonet.iso8583.message.Request0800;
import us.gonet.serializable.data.ISO;

import java.util.LinkedHashMap;
import java.util.Map;

@Component ( "messageHandler" )
public class MessageHandler {

    @Autowired
    MessageController mc;

    private boolean logonFlag = false;


    public ISO sendMessage ( ISO iso ) {
        return mc.sendATM( iso );
    }

    public ISO sendMessageNMM ( ISO iso ) {
        return mc.sendNNM( iso );
    }

    public String sendLogon () {
        Map < String, String > dataElements = new LinkedHashMap <>();
        dataElements.put( "p11", "000001" );
        dataElements.put( "s70", "Logon" );
        Request0800 request0800 = new Request0800( dataElements );
        if ( mc.isRunning() ) {
            if ( ! logonFlag ) {
                logonFlag = true;
                ISO response = sendMessageNMM( request0800.getIso() );
                if ( ! response.isTimeOut() ) {
                    return Errors.LOGON_COMPLETE.getValue();
                } else {
                    logonFlag = false;
                    return Errors.LOGON_TIMEOUT.getValue();
                }
            } else {
                return Errors.LOGON_EXIST.getValue();
            }
        }
        return Errors.SOCKET_STATUS_OFF.getValue();
    }

    public String sendLogoff () {
        Map < String, String > dataElements = new LinkedHashMap <>();
        dataElements.put( "p11", "900001" );
        dataElements.put( "s70", "Logoff" );
        Request0800 request0800 = new Request0800( dataElements );
        if ( mc.isRunning() ) {
            if ( logonFlag ) {
                mc.stopEchoTask();
                ISO response = sendMessageNMM( request0800.getIso() );
                if ( response != null ) {
                    logonFlag = false;
                    return Errors.LOGOFF_COMPLETE.getValue();
                }
            } else {
                return Errors.LOGOFF_ALREADY_OFF.getValue();
            }
        }
        return Errors.LOGOFF_ALREADY_OFF.getValue();
    }

    public String sendEcho () {
        if ( mc.isRunning() ) {
            if ( logonFlag ) {
                mc.startEchoTask();
                return Errors.ECHO.getValue();
            }
            return Errors.LOGON_NOT_EXIST.getValue();
        }
        return Errors.SOCKET_CLOSE.getValue();
    }

    public String turnOnSocket () {
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

    public void setLogonFlag ( boolean logonFlag ) {
        this.logonFlag = logonFlag;
    }

    public boolean statusSocket () {
        return mc.isRunning();
    }

    public boolean statusLog () {
        return logonFlag;
    }
}

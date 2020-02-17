package us.gonet.r8583.rest;

import org.springframework.stereotype.Component;

@Component( "restServices" )
public class RestServices {

    private String isoUrl;
    private String jkeUrl;
    private String rcptUrl;
    private String jdbUrl;

    public String getIsoUrl () {
        return isoUrl;
    }

    public void setIsoUrl ( String isoUrl ) {
        this.isoUrl = isoUrl;
    }

    public String getJkeUrl () {
        return jkeUrl;
    }

    public void setJkeUrl ( String jkeUrl ) {
        this.jkeUrl = jkeUrl;
    }

    public String getRcptUrl () {
        return rcptUrl;
    }

    public void setRcptUrl ( String rcptUrl ) {
        this.rcptUrl = rcptUrl;
    }

    public String getJdbUrl () {
        return jdbUrl;
    }

    public void setJdbUrl ( String jdbUrl ) {
        this.jdbUrl = jdbUrl;
    }
}

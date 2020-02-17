package us.gonet.rcpt.core.utils;

import org.springframework.stereotype.Component;

@Component ( "localRestServices" )
public class LocalRestServices {

    private String jdbUrl;

    public String getJdbUrl () {
        return jdbUrl;
    }

    public void setJdbUrl ( String jdbUrl ) {
        this.jdbUrl = jdbUrl;
    }
}

package us.gonet.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import us.gonet.security.auth.AuthIn;

import java.util.Arrays;

@Component ( "restUtils" )
public class RestUtils {

    @Autowired
    AuthIn authIn;
    private static final String HEADER_AUTH = "Authorization";
    private RestUtils (){}

    public  HttpHeaders buildHttpHeaders( String serviceToken ){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );
        headers.add( "Content-Type", "application/json" );
        headers = authHeader( headers, serviceToken );
        return headers;
    }

    public HttpHeaders authHeader( HttpHeaders headers, String serviceToken ){
        switch ( serviceToken ){
            case "iso":
                headers.add( HEADER_AUTH, authIn.getTokenIsoServices() );
                break;
            case "jke":
                headers.add( HEADER_AUTH, authIn.getTokenJkeServices() );
                break;
            case "rcpt":
                headers.add( HEADER_AUTH, authIn.getTokenRCPTServices() );
                break;
            case "jdb":
                headers.add( HEADER_AUTH, authIn.getTokenJDBServices() );
                break;
            default:
                break;
        }
        return headers;
    }
}

package us.gonet.jxiserver.i8583.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import us.gonet.security.auth.AuthIn;
import us.gonet.security.rest.SecurityServices;

import java.util.Arrays;
@Configuration("rUtils")
@Import( {AuthIn.class, SecurityServices.class} )
public class RestUtils {

    private static final String HEADER_AUTH = "Authorization";
    @Autowired
    AuthIn authIn;

    public  HttpHeaders buildHttpHeaders( String serviceToken ){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept( Arrays.asList( MediaType.APPLICATION_JSON ) );
        headers.add( "Content-Type", "application/json" );
        headers = authHeader( headers, serviceToken );
        return headers;
    }

    public HttpHeaders authHeader( HttpHeaders headers, String serviceToken ){
        switch ( serviceToken ){

            case "jke":
                headers.add( HEADER_AUTH, authIn.getTokenJkeServices() );
                break;
            case "wdl":
                headers.add( HEADER_AUTH, authIn.getTokenWDLServices() );
                break;
            case "jdb":
                headers.add( HEADER_AUTH, authIn.getTokenJDBServices() );
                break;
            case "stmt":
                headers.add(HEADER_AUTH, authIn.getTokenSTMTServices());
                break;
            case "inq":
                headers.add(HEADER_AUTH, authIn.getTokenINQServices());
                break;
            case "nch":
                headers.add(HEADER_AUTH, authIn.getTokenNCHServices());
                break;
            case "ges":
                headers.add(HEADER_AUTH, authIn.getTokenGESServices());
                break;
            default:
                break;
        }
        return headers;
    }

}

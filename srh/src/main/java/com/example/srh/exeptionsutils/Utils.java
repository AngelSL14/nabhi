package com.example.srh.exeptionsutils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import us.gonet.security.auth.AuthIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Utils {
    private static final String HEADER_AUTH = "Authorization";
    AuthIn rf;
    public static List< ErrorWS > buildErrors(){
        List< ErrorWS > errors = new ArrayList<>();
        ErrorWS error = new ErrorWS();
        error.setCause( "Invalid response" );
        errors.add( error );
        return errors;
    }

    public static HttpHeaders buildHttpHeaders(String service){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList( MediaType.APPLICATION_JSON ));
        headers.add("Content-Type","application/json");
        if (service.equals("BinDB")){
            headers.add(HEADER_AUTH,"TOKEN");
        }
        return headers;
    }

}

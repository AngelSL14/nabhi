package com.dksits.jke.core.util;

import com.dksits.jke.exceptionutils.Errors;
import com.dksits.jke.model.ServiceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;

import java.net.URI;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 *
 * @author Gustavo Mancilla Flores
 */
public class Utilities {

    private static final Logger LOG = LoggerFactory.getLogger( Utilities.class );
    private Utilities(){}

    public static String setRandomSequence(){
        StringBuilder str = new StringBuilder();
        try {
            Random random = SecureRandom.getInstanceStrong();
            final long streamSize = 6;
            SecureRandom ranGen = new SecureRandom();
            final int min = ranGen.nextInt(2);
            int max = ranGen.nextInt(11)+min+1;
            if(max > 10 || max <4){
                max =10;
            }
            IntStream intStream = random.ints(streamSize,min , max);
            Iterator iterator = intStream.iterator();
        while (iterator.hasNext()){
            str.append( iterator.next() );
        }
        } catch ( NoSuchAlgorithmException e) {
            LOG.error( "Error RandomSequence" );
        }
        return str.toString();
    }

    public static String formatKey ( String key, String keyLen ) {
        StringBuilder builderKey = new StringBuilder();
        int len = Integer.parseInt( keyLen, 16 );
        for ( int i = 0, j = 3; i < len; i=j, j=j+3 ) {
            int hex = Integer.parseInt( key.substring( i, j ) );
            if ( hex < 0 ) {
                builderKey.append(String.format("%02X", hex));
            } else if ( hex < 15 ) {
                builderKey.append("0").append(Integer.toHexString(hex));
            } else {
                builderKey.append(Integer.toHexString(hex));
            }
        }
        return builderKey.toString().toUpperCase();
    }

    public static ResponseWrapper< ServiceInfo > setResponseError( String response ){
        ResponseWrapper< ServiceInfo > responseWrapper = new ResponseWrapper<>();
        List< ServiceInfo > body = new ArrayList<>();
        List< ErrorWS > errors = new ArrayList<>();
        ErrorWS error = new ErrorWS();
        error.setCause( Errors.getCodeForValue( response ) );
        error.setErrorMessage( response );
        errors.add( error );
        responseWrapper.setErrorWS( errors );
        responseWrapper.setBody( body );
        return responseWrapper;
    }

    public static String isCharacter(String search){
        StringBuilder sb = new StringBuilder(search.length());
        for (int i = 0; i < search.length(); ++i) {
            char ch = search.charAt(i);
            if (Character.isLetterOrDigit(ch) || ch == ' ' || ch == '=') {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static String sanitize(String input){
        final String message = Pattern.quote(input);
        return isCharacter(message.substring(2,message.length()-2));
    }

    public static boolean urlWhiteList(URI url)
    {
        return url.getScheme().startsWith("http");
    }

}

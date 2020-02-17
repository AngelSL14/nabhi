package us.gonet.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import us.gonet.security.rest.model.User;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Component ( "utils" )
class Utils {


    private static final Logger LOG = LoggerFactory.getLogger( Utils.class );
    private static final String ERROR = "Invalid Data from AUTH Request";

    User verifyDataFromRemoteRequest ( HttpServletRequest request ) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
            inputStream = request.getInputStream();
            if ( inputStream != null ) {
                bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );
                char[] charBuffer = new char[ 1024 ];
                int bytesRead;
                while ( ( bytesRead = bufferedReader.read( charBuffer ) ) > 0 ) {
                    stringBuilder.append( charBuffer, 0, bytesRead );
                }
            }
        } catch ( IOException e ) {
            LOG.error( ERROR );
        }
        if ( bufferedReader != null ) {
            try {
                bufferedReader.close();
            } catch ( IOException ex ) {
                LOG.error( ERROR );
            }
        }
        return formatData( stringBuilder.toString() );
    }

    private User formatData ( String str ) {
        try{
            str = hexToAscii( str );
        } catch ( NumberFormatException e ){
            LOG.error( ERROR );
            return null;
        }
        int indexOf = str.indexOf( ( char ) 28 );
        if ( indexOf == -1 ){
            LOG.error( ERROR );
            return null;
        }
        String u = str.substring( 0, indexOf );
        str = str.substring( indexOf + 1 );
        indexOf = str.indexOf( ( char ) 28 );
        String p = str.substring( 0, indexOf );
        if ( ( indexOf = str.indexOf( ( char ) 28 ) ) > -1  ){
            byte r = Byte.parseByte( str.substring( indexOf + 1 ) );
            return new User( u, p, r );
        }
        return new User( u, p, ( byte ) 1 );
    }

    String asciiToHex ( String str ) {
        char[] chars = str.toCharArray();
        StringBuilder hex = new StringBuilder();
        for ( char aChar : chars ) {
            hex.append( Integer.toHexString( ( int ) aChar ) );
        }
        return hex.toString().toUpperCase();
    }

    private String hexToAscii ( String hex ) {
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < hex.length() - 1; i += 2 ) {
            String output = hex.substring( i, ( i + 2 ) );
            int decimal = Integer.parseInt( output, 16 );
            sb.append( ( char ) decimal );
        }
        return sb.toString();
    }
}

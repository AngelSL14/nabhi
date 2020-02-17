package us.gonet.rcpt.core.utils;

import org.springframework.stereotype.Component;
import us.gonet.serializable.data.ISO;
import us.gonet.token.Token;
import us.gonet.utils.STMTDecoder;
import us.gonet.utils.TokenDecoder;
import us.gonet.utils.Utilities;

import java.util.List;

@Component( "utilsRcpt" )
public class UtilsRcpt {


    public String getP( String str ){
        int indexOf = str.indexOf( '=' );
        return str.substring( 0, indexOf );
    }

    public float getSurcharge( ISO iso ){
        TokenDecoder tokenDecoder = new TokenDecoder( iso.getDataElements().get( 125 ).getContentField().getBytes() );
        Token token = tokenDecoder .getTokens().getToken( "25" );
        if ( token != null ){
            return Float.parseFloat( token.getToken().get( 1 ).getContentField() ) /100;
        }else {
            return 0.00f;
        }
    }

    public String setSpaces( String str, String format ){
        StringBuilder stringBuilder = new StringBuilder();
        String numSpaces;
        String charControl;
        if ( Utilities.isNumeric( str.substring( 0, 1 ) ) ){
            numSpaces = getNumSpaces( str );
            str = str.substring( numSpaces.length() );
            charControl = getCharControl( str );
            stringBuilder.append( leftPadding( Integer.parseInt( numSpaces ), format, charControl ) );
        }else {
            charControl = getCharControl( str );
            str = str.substring( charControl.length() );
            numSpaces = getNumSpaces( str );
            stringBuilder.append( rightPadding( Integer.parseInt( numSpaces ), format, charControl ) );
        }
        return stringBuilder.toString();
    }

    private String getNumSpaces( String str ){
        StringBuilder numSpaces = new StringBuilder();
        while ( Utilities.isNumeric( str.substring( 0, 1 ) ) ){
            numSpaces.append( str.substring( 0, 1 ) );
            str = str.substring( 1 );
        }
        return numSpaces.toString();
    }

    private String getCharControl( String str ){
        StringBuilder charControl = new StringBuilder();
        while ( !Utilities.isNumeric( str.substring( 0, 1 )  ) && !str.substring( 0, 1 ).equals( "]" ) ){
            charControl.append( str.substring( 0, 1 ) );
            str = str.substring( 1 );
        }
        return charControl.toString();
    }

    private String leftPadding ( int value, String str, String control ) {
        StringBuilder s = new StringBuilder();
        int result = getValue( value, str, control );
        for ( int i = 0; i < result; i++ ) {
            s.append( " " );
        }
        s.append( str );
        return s.toString();
    }

    private String rightPadding ( int value, String str, String control ) {
        StringBuilder s = new StringBuilder( str );
        int result = getValue( value, str, control );
        for ( int i = 0; i < result; i++ ) {
            s.append( " " );
        }
        return s.toString();
    }

    private int getValue( int value, String str, String control ){
        int result = 39 - ( value + str.length() );
        if ( control.equals( "IT" ) ){
            result = value;
        }
        return result;
    }

    public String getSTMT( ISO iso ){
        StringBuilder str1 = new StringBuilder();
        String str = "";
        List< String > stmt = new STMTDecoder().decode( iso.getDataElements().get( 124 ).getContentField() );
        for ( String s : stmt ) {
            str = s + ( char ) AsciiNonPrintable.LF.getValue();
            str1.append( str );
        }
        return str1.toString();
    }


}

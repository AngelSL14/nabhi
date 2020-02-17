package com.dksits.jke.core.message;

public class MessageUtils {

    private MessageUtils(){
    }

    protected static String fillSpaces( String str ){
        StringBuilder stringBuilder = new StringBuilder( str );
        int count = str.length();
        for ( ; count < 16; count ++ ) {
            stringBuilder.append( " " );
        }
        return stringBuilder.toString();
    }

    protected static String extract ( String ptr ) {
        int indexOf = ptr.indexOf( ( char ) 28 );
        if ( indexOf == -1 ) {
            return ptr;
        }
        return ptr.substring( 0, indexOf );
    }

    protected static String scandFS ( String ptr ) {
        int indexOf = ptr.indexOf( ( char ) 28 );
        return ptr.substring( indexOf + 1 );
    }

}

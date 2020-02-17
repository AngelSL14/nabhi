package com.dksits.jke.core.message;

import com.dksits.jke.core.util.Utilities;
import com.google.gson.Gson;

public class TranslatePinBlockResponse implements Response {


    public static class TranslatePinBlockBuilder {


        public TranslatePinBlockResponse build ( String message ) {
            String seq;
            String ipk = "";
            String clazz = MessageUtils.extract( message );
            message = MessageUtils.scandFS( message );
            String err = MessageUtils.extract( message );
            message = MessageUtils.scandFS( message );
            if ( err.equals( "0" ) ) {
                String pinBlockLen = MessageUtils.extract( message ).substring( 0, 3 );
                String pinBlock = MessageUtils.extract( message ).substring( 3 );
                pinBlock = Utilities.formatKey( pinBlock, pinBlockLen );
                message = MessageUtils.scandFS( message );
                if ( ! MessageUtils.extract( message ).equals( "" ) ) {
                    String ipkLength = MessageUtils.extract( message ).substring( 0, 3 );
                    ipk = MessageUtils.extract( message ).substring( 3 );
                    ipk = Utilities.formatKey( ipk, ipkLength );
                }
                message = MessageUtils.scandFS( message );
                seq = MessageUtils.extract( message );
                return new TranslatePinBlockResponse( clazz, err, pinBlock, ipk, seq );
            } else {
                message = MessageUtils.scandFS( message );
                message = MessageUtils.scandFS( message );
                seq = MessageUtils.extract( message );
                return new TranslatePinBlockResponse( clazz, err, "", "", seq );
            }

        }

    }

    private String clazz;
    private String error;
    private String pinBlock;
    private String ipk;
    private String sequence;
    private String mode;

    public TranslatePinBlockResponse () {
    }

    TranslatePinBlockResponse ( String clazz, String error, String pinBlock, String ipk, String sequence ) {
        this.clazz = clazz;
        this.error = error;
        this.pinBlock = pinBlock;
        this.ipk = ipk;
        this.sequence = sequence;
    }

    public String getClazz () {
        return clazz;
    }

    public String getTPK () {
        return ipk;
    }

    @Override
    public String getMODE () {
        return mode;
    }

    @Override
    public String getBLOCK () {
        return pinBlock;
    }

    @Override
    public void setError ( String error ) {
        this.error = error;
    }

    @Override
    public String getSequence () {
        return sequence;
    }

    public String gerError () {
        return error;
    }

    @Override
    public String toString () {
        return new Gson().toJson( this );
    }
}

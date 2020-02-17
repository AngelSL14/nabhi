/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dksits.jke.core.message;


import com.dksits.jke.core.util.Utilities;
import com.google.gson.Gson;

public class KeyExchangeResponse implements Response {

    

    public static class KeyResponseBuilder {

        public KeyExchangeResponse build ( String message ) {
            String seq;
            String clazz = MessageUtils.extract( message );
            message = MessageUtils.scandFS( message );
            String err = MessageUtils.extract( message );
            message = MessageUtils.scandFS( message );
            if ( err.equals( "0" ) ){
                String encrypType = MessageUtils.extract( message );
                message = MessageUtils.scandFS( message );
                String tpkLength = MessageUtils.extract( message ).substring( 0, 3 );
                String tpk = MessageUtils.extract( message ).substring( 3 );
                tpk = Utilities.formatKey( tpk, tpkLength );
                message = MessageUtils.scandFS( message );
                seq = MessageUtils.extract( message );
                return new KeyExchangeResponse( clazz, err, encrypType, tpk, seq );
            }else{
                message = MessageUtils.scandFS( message );
                message = MessageUtils.scandFS( message );
                seq = MessageUtils.extract( message );
                return new KeyExchangeResponse( clazz, err ,"", "", seq );
            }
            
        }
    }

    private String clazz;
    private String error;
    private String encrypType;
    private String mode;
    private String sequence;
    private String block;

    public KeyExchangeResponse () {
    }

    KeyExchangeResponse ( String clazz, String error, String encrypType, String mode, String sequence ) {
        this.clazz = clazz;
        this.error = error;
        this.encrypType = encrypType;
        this.mode = mode;
        this.sequence = sequence;
    }

    public String getClazz() {
        return clazz;
    }

    public String getEncrypType() {
        return encrypType;
    }

    @Override
    public String getSequence () {
        return sequence;
    }
    
    @Override
    public String getMODE () {
        return mode;
    }

    public String getError(){return error;}

    @Override
    public String getBLOCK () {
        return block;
    }

    @Override
    public void setError ( String error ) {
        this.error = error;
    }

    @Override
    public String toString () {
        return new Gson().toJson( this );
    }

}

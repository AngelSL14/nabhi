package com.dksits.jke.core.message;

public interface Response {
    
    String getSequence ();
    String getMODE ();
    String getBLOCK ();
    void setError ( String error );
    
}

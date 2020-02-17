package com.example.srh.core.rest;

public enum  PostServices {

    BIN_DPROSA          ( "/bin/like/" ),
    ATD_DPROSA          ( "/atd/" )
    ;
    private String url;
    PostServices( String url ){
        this.url = url;
    }
    public String getUrl () {
        return url;
    }


}

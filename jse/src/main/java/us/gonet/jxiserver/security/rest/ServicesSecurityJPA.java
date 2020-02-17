package us.gonet.jxiserver.security.rest;

public enum ServicesSecurityJPA {

    //DOCKER
//    API_ISO_PROCESS      ( System.getenv( "URL_ISO" ) + "/iso/process" ),
//    API_JKE_PIN_BLOCK    ( System.getenv( "URL_JKE" ) + "/sock/sendMessagePinBlock" ),
//    API_RCPT_CREATE_RCPT ( System.getenv( "URL_RCPT" ) + "/rcpt/createRCPT" ),
//    API_ISO_AUTH         ( System.getenv( "URL_ISO" ) + C.AUTH ),
//    API_JKE_AUTH         ( System.getenv( "URL_JKE" ) + C.AUTH ),
//    API_RCPT_AUTH        ( System.getenv( "URL_RCPT" ) + C.AUTH ),

    //IDE
    API_WDL_AUTH         ( "http://localhost:8083/" + C.AUTH ),
    API_INQ_AUTH         ( "http://localhost:8085/" + C.AUTH ),
    API_GES_AUTH         ( "http://localhost:8086/" + C.AUTH ),
    API_NCH_AUTH         ( "http://localhost:8087/" + C.AUTH ),
    API_STMT_AUTH        ( "http://localhost:8088/" + C.AUTH ),
    API_JKE_AUTH         ( "http://localhost:8084/" + C.AUTH ),

    API_JPA_CREATE_TOKEN  ( "http://10.255.11.148:8080/tokenUsers/"),
    API_JPA_FIND_TOKEN    ( "http://10.255.11.148:8080/tokenUsers/search/byId" ),

    ;

    private String url;

    ServicesSecurityJPA ( String url ){
        this.url = url;
    }

    public String getUrl () {
        return url;
    }

    private class C {
        static final String AUTH = "/auth";
    }


}

package us.gonet.jxiserver.i8583.rest;

public enum ServicesJPA {
    //DOCKER
//    API_ISO_PROCESS      ( System.getenv( "URL_ISO" ) + "/iso/process" ),
//    API_JKE_PIN_BLOCK    ( System.getenv( "URL_JKE" ) + "/sock/sendMessagePinBlock" ),
//    API_RCPT_CREATE_RCPT ( System.getenv( "URL_RCPT" ) + "/rcpt/createRCPT" ),
//    API_ISO_AUTH         ( System.getenv( "URL_ISO" ) + C.AUTH ),
//    API_JKE_AUTH         ( System.getenv( "URL_JKE" ) + C.AUTH ),
//    API_RCPT_AUTH        ( System.getenv( "URL_RCPT" ) + C.AUTH ),
    //IDE
//    API_ISO_PROCESS      ( "http://localhost:8081/" + "/iso/process" ),
//    API_JKE_PIN_BLOCK    ( "http://localhost:8080/" + "/sock/sendMessagePinBlock" ),
//    API_RCPT_CREATE_RCPT ( "http://localhost:8082/"  + "/rcpt/createRCPT" ),
//    API_ISO_AUTH         ( "http://localhost:8081/" + C.AUTH ),
//    API_JKE_AUTH         ( "http://localhost:8080/" + C.AUTH ),
//    API_RCPT_AUTH        ( "http://localhost:8082/"  + C.AUTH ),
//
    //TOMCAT
    API_JPA_ATD( "http://10.255.11.148:8080/atd/" ),
    API_JPA_APC( "http://10.255.11.148:8080/apc/" ),


    ;

    private String url;

    ServicesJPA ( String url ){
        this.url = url;
    }

    public String getUrl () {
        return url;
    }

}

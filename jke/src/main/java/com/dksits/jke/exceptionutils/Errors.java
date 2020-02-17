package com.dksits.jke.exceptionutils;

public enum Errors {

    LOGON_COMPLETE         ( C.JKE + "00","LOGON COMPLETE"),
    LOGOFF_COMPLETE        ( C.JKE + "00","LOGOFF COMPLETE"),
    LOGOFF_ALREADY_OFF     ( C.JKE + "-01","LOGOFF ALREADY OFF"),
    LOGON_TIMEOUT          ( C.JKE + "-02","LOGON TIMEOUT"),
    LOGON_EXIST            ( C.JKE + "-03","LOGON ALREADY EXIST"),
    LOGON_NOT_EXIST        ( C.JKE + "-04","LOGON NOT EXIST"),
    SOCKET_STATUS_OFF      ( C.JKE + "-05","SOCKET STATUS OFF (HAVE TO BE ON)"),
    SOCKET_OFF             ( C.JKE + "-06","EL SOCKET NO ESTA ENCENDIDO" ),
    SOCKET_OPEN            ( C.JKE + "00","SOCKET ON" ),
    SOCKET_OPEN_ERROR	   ( C.JKE + "-07","ERROR WHILE OPENING SOCKET" ),
    SOCKET_CLOSE           ( C.JKE + "00","SOCKET CLOSED"),
    SOCKET_CLOSE_ERROR     ( C.JKE + "-08","ERROR CLOSING THE SOCKET"),
    SOCKET_ALREADY_CLOSED  ( C.JKE + "00","SOCKET ALREADY CLOSED"),
    SOCKET_ALREADY_ON      ( C.JKE + "00","SOCKET ALREADY ON"),
    ECHO                   ( C.JKE + "00","ECHO EXECUTED"),
    LOGOFF                 ( C.JKE + "00","EL LOG ESTA INACTIVO" ),
    BANK_PROBLEM           ( C.JKE + "-09","HUBO UN PROBLEMA AL CONTACTAR EL BANCO" ),
    TEST_FAILED            ( C.JKE + "-10","TEST COMMAND FAILURE" ),
    TEST_COMMAND           ( C.JKE + "00","TEST COMMAND EXECUTE" ),
    API_REST_ERROR         ( C.JKE + "-11", "ERROR CON EL SERVICIO REST JDB"),
    OPERATION              ( C.JKE + "00","SERVICE OPERATION EXECUTE" ),
    INVALID_NODE_NAME      ( C.JKE + "-12", "INVALID NODE NAME" );

    private String value;
    private String code;

    Errors ( String code ,String value ) {
        this.code = code;
        this.value = value;
    }

    public String getValue () {
        return value;
    }
    public String getCode () {
        return code;
    }

    public static String getCodeForValue( String value ){
        for ( Errors e : Errors.values () ){
            if ( e.getValue ().equals ( value ) ){
                return e.getCode ();
            }
        }
        return "-11";
    }

    private class C {
        static final String JKE = "JKE";
    }
}

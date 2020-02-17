package us.gonet.iso.core.utils;

public enum Errors {

    LOGON_COMPLETE         ( C.ISO + "00","LOGON COMPLETE"),
    LOGOFF_COMPLETE        ( C.ISO + "00","LOGOFF COMPLETE"),
    LOGOFF_ALREADY_OFF     ( C.ISO + "-01","LOGOFF ALREADY OFF"),
    LOGON_TIMEOUT          ( C.ISO + "-02","LOGON TIMEOUT"),
    LOGON_EXIST            ( C.ISO + "-03","LOGON ALREADY EXIST"),
    LOGON_NOT_EXIST        ( C.ISO + "-04","LOGON NOT EXIST"),
    SOCKET_STATUS_OFF      ( C.ISO + "-05","SOCKET STATUS OFF (HAVE TO BE ON)"),
    SOCKET_OFF             ( C.ISO + "-06","EL SOCKET NO ESTA ENCENDIDO" ),
    SOCKET_OPEN            ( C.ISO + "00","SOCKET ON" ),
    SOCKET_OPEN_ERROR	   ( C.ISO + "-07","ERROR WHILE OPENING SOCKET" ),
    SOCKET_CLOSE           ( C.ISO + "00","SOCKET CLOSED"),
    SOCKET_CLOSE_ERROR     ( C.ISO + "-08","ERROR CLOSING THE SOCKET"),
    SOCKET_ALREADY_CLOSED  ( C.ISO + "00","SOCKET ALREADY CLOSED"),
    SOCKET_ALREADY_ON      ( C.ISO + "00","SOCKET ALREADY ON"),
    ECHO                   ( C.ISO + "00","ECHO EXECUTED"),
    LOGOFF                 ( C.ISO + "00","EL LOG ESTA INACTIVO" ),
    BANK_PROBLEM           ( C.ISO + "-09","HUBO UN PROBLEMA AL CONTACTAR EL BANCO" ),
    TEST_FAILED            ( C.ISO + "-10","TEST COMMAND FAILURE" ),
    API_REST_ERROR         ( C.ISO + "-11", "ERROR CON EL SERVICIO REST JDB"),
    TEST_COMMAND           ( C.ISO + "00","TEST COMMAND EXECUTE" ),
    OPERATION              ( C.ISO + "00","SERVICE OPERATION EXECUTE" ),
    INVALID_NODE_NAME      ( C.ISO + "-12", "INVALID NODE NAME" ),
    INVALID_CONFIG         ( C.ISO + "-13", "INVALID CONFIG" );


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
        static final String ISO = "ISO";
    }
}

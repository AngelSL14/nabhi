package us.gonet.rcpt.core;

public enum ReceiptConstants {

    DA    ,
    T1    ,
    AT    ,
    TC    ,
    PAN   ,
    AC    ,
    FC    ,
    OB    ,
    AMT   ,
    SRH   ,
    IVA   ,
    TT    ,
    CB    ,
    AID   ,
    ARQC  ,
    ARPC  ,
    PHN   ,
    COMP  ,
    STMT  ,
    OWNER ,
    LOC   ,
    ;
    ReceiptConstants (){}

    public static ReceiptConstants getValue( String name ){
        return ReceiptConstants.valueOf( name );
    }
}

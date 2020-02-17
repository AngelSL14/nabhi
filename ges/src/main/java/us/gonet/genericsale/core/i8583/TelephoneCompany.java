package us.gonet.genericsale.core.i8583;

public enum TelephoneCompany {

    TELCEL( "TELC", "0000" ),
    MOVISTAR( "PEGA", "0000" ),
    IUSA( "IUSA", "0000" ),
    ATT( "IUSA", "0000" );


    TelephoneCompany( String company, String code ) {
        this.company = company;
        this.code = code;
    }

    private String company;
    private String code;

    public String getCompany() {
        return company;
    }

    public String getCode() {
        return code;
    }

    public static TelephoneCompany getValue( String comp ) {
        for ( TelephoneCompany c : values() ) {
            if ( c.name().equals( comp ) ) {
                return c;
            }
        }
        return null;
    }
}

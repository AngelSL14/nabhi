package us.gonet.jxiserver.i8583.rest;

public enum Services {

    //Tomcat
//   API_WDL    ( "http://localhost:8080/wdl/wdl/sendWithdrawal"   ),
//   API_INQ    ( "http://localhost:8080/inq/inq/sendBalanceInquiry"   ),
//   API_GES    ( "http://localhost:8080/ges/ges/sendGenericSale"   ),
//   API_NCH    ( "http://localhost:8080/nch/nch/sendNipChange"   ),
//   API_STMT   ( "http://localhost:8080/stmt/stmt/sendStatement" ),

    //IDE
 /*   API_WDL    ( "http://localhost:8083/wld/sendWithdrawal" ),
    API_INQ    ( "http://localhost:8085/inq/sendBalanceInquiry" ),
    API_GES    ( "http://localhost:8086/ges/sendGenericSale" ),
    API_NCH    ( "http://localhost:8087/nch/sendNipChange" ),
    API_STMT   ( "http://localhost:8088/stmt/sendStatement" ),
*/
    API_WDL    ( "localhost:8083/wld/sendWithdrawal" ),
    API_INQ    ( "http://localhost:8085/inq/sendBalanceInquiry" ),
    API_GES    ( "http://localhost:8086/ges/sendGenericSale" ),
    API_NCH    ( "http://localhost:8087/nch/sendNipChange" ),
    API_STMT   ( "http://localhost:8088/stmt/sendStatement" ),

    ;
    private String url;


    Services ( String url ){
        this.url = url;
    }

    public String getUrl () {
        return url;
    }


}

package us.gonet.serverutils.model.jdb;

public class Cmp {

    private String key;
    private String termFiid;
    private String termGroup;
    private String tranCode;
    private String procCode;
    private String fiCode;
    private String fiName;
    private String amount;
    private String userField;

    public String getKey ( ) {
        return key;
    }

    public void setKey ( String key ) {
        this.key = key;
    }

    public String getTermFiid ( ) {
        return termFiid;
    }

    public void setTermFiid ( String termFiid ) {
        this.termFiid = termFiid;
    }

    public String getTermGroup ( ) {
        return termGroup;
    }

    public void setTermGroup ( String termGroup ) {
        this.termGroup = termGroup;
    }

    public String getTranCode ( ) {
        return tranCode;
    }

    public void setTranCode ( String tranCode ) {
        this.tranCode = tranCode;
    }

    public String getProcCode ( ) {
        return procCode;
    }

    public void setProcCode ( String procCode ) {
        this.procCode = procCode;
    }

    public String getFiCode ( ) {
        return fiCode;
    }

    public void setFiCode ( String fiCode ) {
        this.fiCode = fiCode;
    }

    public String getFiName ( ) {
        return fiName;
    }

    public void setFiName ( String fiName ) {
        this.fiName = fiName;
    }

    public String getAmount ( ) {
        return amount;
    }

    public void setAmount ( String amount ) {
        this.amount = amount;
    }

    public String getUserField ( ) {
        return userField;
    }

    public void setUserField ( String userField ) {
        this.userField = userField;
    }

    @Override
    public String toString ( ) {
        return "Cmp{" +
                "key='" + key + '\'' +
                ", termFiid='" + termFiid + '\'' +
                ", termGroup='" + termGroup + '\'' +
                ", tranCode='" + tranCode + '\'' +
                ", procCode='" + procCode + '\'' +
                ", fiCode='" + fiCode + '\'' +
                ", fiName='" + fiName + '\'' +
                ", amount='" + amount + '\'' +
                ", userField='" + userField + '\'' +
                '}';
    }
}

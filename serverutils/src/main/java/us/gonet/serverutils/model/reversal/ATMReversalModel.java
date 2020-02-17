package us.gonet.serverutils.model.reversal;

import com.google.gson.GsonBuilder;

public class ATMReversalModel {

    private String message;
    private String reversalCode;
    private int dispensedAmount;

    public String getMessage () {
        return message;
    }

    public void setMessage ( String message ) {
        this.message = message;
    }

    public String getReversalCode () {
        return reversalCode;
    }

    public void setReversalCode ( String reversalCode ) {
        this.reversalCode = reversalCode;
    }

    public int getDispensedAmount () {
        return dispensedAmount;
    }

    public void setDispensedAmount ( int dispensedAmount ) {
        this.dispensedAmount = dispensedAmount;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}

package us.gonet.serverutils.model.pse;

import com.google.gson.GsonBuilder;
import us.gonet.serverutils.model.ATMRequestModel;

public class PaymentRequest {

    private Payment payment;
    private String amount;
    private ATMRequestModel atmRequestModel;

    public Payment getPayment () {
        return payment;
    }

    public void setPayment ( Payment payment ) {
        this.payment = payment;
    }

    public String getAmount () {
        return amount;
    }

    public void setAmount ( String amount ) {
        this.amount = amount;
    }

    public ATMRequestModel getAtmRequestModel () {
        return atmRequestModel;
    }

    public void setAtmRequestModel ( ATMRequestModel atmRequestModel ) {
        this.atmRequestModel = atmRequestModel;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}

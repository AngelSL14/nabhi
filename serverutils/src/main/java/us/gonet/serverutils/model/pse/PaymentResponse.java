package us.gonet.serverutils.model.pse;

import com.google.gson.GsonBuilder;
import us.gonet.serverutils.model.ATMResponseModel;

public class PaymentResponse {

    private Payment payment;
    private String authNumber;
    private ATMResponseModel atmResponseModel;

    public Payment getPayment () {
        return payment;
    }

    public void setPayment ( Payment payment ) {
        this.payment = payment;
    }

    public String getAuthNumber () {
        return authNumber;
    }

    public void setAuthNumber ( String authNumber ) {
        this.authNumber = authNumber;
    }

    public ATMResponseModel getAtmResponseModel () {
        return atmResponseModel;
    }

    public void setAtmResponseModel ( ATMResponseModel atmResponseModel ) {
        this.atmResponseModel = atmResponseModel;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}

package us.gonet.serverutils.model;

import com.google.gson.GsonBuilder;

public class RCPTRequestModel {

    private ATMRequestModel atmRequestModel;
    private String message;

    public ATMRequestModel getAtmRequestModel () {
        return atmRequestModel;
    }

    public void setAtmRequestModel ( ATMRequestModel atmRequestModel ) {
        this.atmRequestModel = atmRequestModel;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage ( String message ) {
        this.message = message;
    }

    @Override
    public String toString () {
        return new GsonBuilder().setPrettyPrinting().create().toJson( this );
    }
}

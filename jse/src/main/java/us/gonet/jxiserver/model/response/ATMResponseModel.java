package us.gonet.jxiserver.model.response;

import us.gonet.serializable.data.ISO;

public class ATMResponseModel {

    private String receipt;
    private ISO message;

    public String getReceipt () {
        return receipt;
    }

    public void setReceipt ( String receipt ) {
        this.receipt = receipt;
    }

    public ISO getMessage () {
        return message;
    }

    public void setMessage ( ISO message ) {
        this.message = message;
    }

}

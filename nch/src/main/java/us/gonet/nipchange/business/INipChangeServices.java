package us.gonet.nipchange.business;

import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;

public interface INipChangeServices {

    ResponseWrapper sendNipChange( ATMRequestModel atmRequestModel );

}

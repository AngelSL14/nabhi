package us.gonet.genericsale.business;

import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;

public interface IGenericSaleServices {

    ResponseWrapper sendGenericSale( ATMRequestModel atmRequestModel );

}

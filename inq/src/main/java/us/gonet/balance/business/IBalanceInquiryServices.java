package us.gonet.balance.business;

import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;

public interface IBalanceInquiryServices {

    ResponseWrapper sendBalanceInquiry( ATMRequestModel atmRequestModel );

}

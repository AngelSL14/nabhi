package us.gonet.withdrawal.business;

import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;

public interface IWithdrawalServices {

    ResponseWrapper sendWithdrawal ( ATMRequestModel atmRequestModel );

}

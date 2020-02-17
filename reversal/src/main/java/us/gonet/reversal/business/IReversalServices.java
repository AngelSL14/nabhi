package us.gonet.reversal.business;

import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.serverutils.model.reversal.ATMReversalModel;

public interface IReversalServices {

    ResponseWrapper< ATMResponseModel > sendReversal ( ATMReversalModel arm );

}

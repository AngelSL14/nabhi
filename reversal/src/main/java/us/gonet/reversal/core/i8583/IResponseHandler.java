package us.gonet.reversal.core.i8583;

import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.serverutils.model.reversal.ATMReversalModel;

public interface IResponseHandler {

    ResponseWrapper< ATMResponseModel > sendMessage( ATMReversalModel arm );
}

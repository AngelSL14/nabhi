package us.gonet.genericsale.core.i8583;

import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.ATMResponseModel;

public interface IResponseHandler {

    ResponseWrapper< ATMResponseModel > sendMessage( ATMRequestModel wm );
}

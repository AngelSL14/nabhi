package us.gonet.statement.business;

import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;

public interface IStatementServices {

    ResponseWrapper sendStatement( ATMRequestModel atmRequestModel );

}

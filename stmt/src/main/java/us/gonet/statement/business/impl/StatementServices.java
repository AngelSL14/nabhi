package us.gonet.statement.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.statement.business.IStatementServices;
import us.gonet.statement.core.i8583.IResponseHandler;

@Component ( "statementServices" )
public class StatementServices implements IStatementServices {

    @Autowired
    @Qualifier ( "responseHandler" )
    IResponseHandler responseHandler;

    @Override
    public ResponseWrapper< ATMResponseModel > sendStatement( ATMRequestModel atmRequestModel ) {
        return responseHandler.sendMessage( atmRequestModel );
    }
}

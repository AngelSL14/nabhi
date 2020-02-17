package us.gonet.reversal.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.reversal.business.IReversalServices;
import us.gonet.reversal.core.i8583.IResponseHandler;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.serverutils.model.reversal.ATMReversalModel;

@Component ( "reversalServices" )
public class ReversalServices implements IReversalServices {

    @Autowired
    @Qualifier( "responseHandler" )
    IResponseHandler responseHandler;

    @Override
    public ResponseWrapper< ATMResponseModel > sendReversal ( ATMReversalModel arm ) {
        return responseHandler.sendMessage( arm );
    }
}

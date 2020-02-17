package us.gonet.withdrawal.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.withdrawal.business.IWithdrawalServices;
import us.gonet.withdrawal.core.i8583.IResponseHandler;

@Component ( "withdrawalServices" )
public class WithdrawalServices implements IWithdrawalServices {

    @Autowired
    @Qualifier( "responseHandler" )
    IResponseHandler responseHandler;

    @Override
    public ResponseWrapper< ATMResponseModel > sendWithdrawal ( ATMRequestModel atmRequestModel ) {
        return responseHandler.sendMessage( atmRequestModel );
    }
}

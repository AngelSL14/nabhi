package us.gonet.balance.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.balance.business.IBalanceInquiryServices;
import us.gonet.balance.core.i8583.IResponseHandler;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.ATMResponseModel;

@Component ( "balanceInquiryServices" )
public class BalanceInquiryServices implements IBalanceInquiryServices {

    @Autowired
    @Qualifier( "responseHandler" )
    IResponseHandler responseHandler;

    @Override
    public ResponseWrapper< ATMResponseModel > sendBalanceInquiry( ATMRequestModel atmRequestModel ) {
        return responseHandler.sendMessage( atmRequestModel );
    }
}

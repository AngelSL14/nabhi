package us.gonet.rcpt.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.rcpt.business.IRCPTServices;
import us.gonet.rcpt.core.IReceiptHandler;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.RCPTRequestModel;
import us.gonet.serverutils.model.receipt.Receipt;

@Component( "rcptServices" )
public class RCPTServices implements IRCPTServices {

    @Autowired
    @Qualifier( "receiptHandler")
    IReceiptHandler receiptHandler;

    @Override
    public ResponseWrapper< Receipt > createRCPT ( RCPTRequestModel rcptRequestModel ) {
        return receiptHandler.createReceipt( rcptRequestModel.getAtmRequestModel(), rcptRequestModel.getMessage() );
    }
}

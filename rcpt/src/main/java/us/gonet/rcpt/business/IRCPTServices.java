package us.gonet.rcpt.business;

import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.RCPTRequestModel;
import us.gonet.serverutils.model.receipt.Receipt;

public interface IRCPTServices {

    ResponseWrapper< Receipt > createRCPT( RCPTRequestModel rcptRequestModel );

}

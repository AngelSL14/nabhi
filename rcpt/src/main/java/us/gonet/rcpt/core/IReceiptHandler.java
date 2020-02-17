package us.gonet.rcpt.core;

import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.receipt.Receipt;

public interface IReceiptHandler {

    public ResponseWrapper< Receipt > createReceipt( ATMRequestModel ar, String message );
}

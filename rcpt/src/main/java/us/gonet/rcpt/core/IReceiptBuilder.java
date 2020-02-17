package us.gonet.rcpt.core;

import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.receipt.Receipt;

public interface IReceiptBuilder {

    Receipt build(  ATMRequestModel wm, ISO iso, String ... str ) throws ServerException;
}

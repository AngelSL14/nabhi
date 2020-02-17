package us.gonet.jxiserver.business;

import us.gonet.jxiserver.model.request.*;
import us.gonet.jxiserver.model.response.GenericProcess;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.BillsModel;

import java.util.Map;

public interface IProsaBusiness {
    ResponseWrapper <BillsModel> cashWithAuth(CashWithdrawalModel generic) throws ServerException;
    ResponseWrapper <Generic> getCommission (AtmInfo generic) throws ServerException;

    ResponseWrapper<Map<String, String>> balInquiryAuth(Generic generic) throws ServerException;

    ResponseWrapper<String> listTrx( Generic generic) throws ServerException;

    ResponseWrapper<GenericProcess> changePin( ChangeNipModel generic) throws ServerException;

    ResponseWrapper<GenericProcess> genericSale(GenericSaleModel generic) throws ServerException;


}

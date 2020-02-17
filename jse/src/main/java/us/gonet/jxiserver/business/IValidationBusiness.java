package us.gonet.jxiserver.business;

import us.gonet.jxiserver.model.request.CashWithdrawalModel;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.jdb.BillsModel;
import us.gonet.serverutils.model.jdb.Casetero;

public interface IValidationBusiness {

    ResponseWrapper<BillsModel> validateWithdrawal(CashWithdrawalModel generic);

    ResponseWrapper<Casetero> validateMinAmount(Generic generic);
}

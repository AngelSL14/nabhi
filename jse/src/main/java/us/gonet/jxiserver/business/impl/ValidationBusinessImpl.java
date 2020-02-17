package us.gonet.jxiserver.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.business.IValidationBusiness;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.model.request.CashWithdrawalModel;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.jxiserver.utils.UrlFilter;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.BillsModel;
import us.gonet.serverutils.model.jdb.Casetero;

import java.util.ArrayList;
import java.util.List;

@Component("validationBus")
public class ValidationBusinessImpl implements IValidationBusiness {


    @Autowired
    private RestRequestFactory restRequestFactory;

    @Autowired
    private UrlFilter urlFilter;

    @Override
    public ResponseWrapper<BillsModel> validateWithdrawal(CashWithdrawalModel generic){
        ResponseWrapper<BillsModel> bills = new ResponseWrapper<>();
        String errorMessage = "Error al calcular el retiro";
        try {
            Generic bod = CashWithdrawalModel.builder()
                    .withCashWithAmount(urlFilter.sanitizeString(generic.getCashWithAmount()))
                    .withIp(urlFilter.sanitizeString(generic.getIp()))
                    .build();

            bills = restRequestFactory.buildRestRequest("/adp/getBills", BillsModel.class, "adp",
                    errorMessage, "post", bod, null);
            bills.setCode("200");
        } catch (ServerException e) {
            List<ErrorWS> errorWS = new ArrayList<>();
            errorWS.add(new ErrorWS("-JXI10", "No se puede dispensar el monto: "+generic.getCashWithAmount()));
            errorWS.addAll( e.getErrors());
            bills.setCode("-500");
            bills.addAllError(errorWS);
        }
        return bills;
    }

    @Override
    public ResponseWrapper<Casetero> validateMinAmount(Generic generic)
    {
        ResponseWrapper<Casetero> bills = new ResponseWrapper<>();
        String errorMessage = "Error al obtener el monto minimo";
        try {
            String service = "/adp".concat("/getMinAmount");
            Generic bod = Generic.builder().withIp(urlFilter.sanitizeString(generic.getIp())).build();
            bills = restRequestFactory.buildRestRequest(service, Casetero.class,
                    "adp", errorMessage, "post", bod, "");
        } catch (ServerException e) {
            List<ErrorWS> errorWS = new ArrayList<>();
            errorWS.add(new ErrorWS("-JXI11", "No se puede recuperar el monto minimo: "+generic.getIp()));
            errorWS.addAll( e.getErrors());
            bills.setCode("00");
            bills.addAllError(errorWS);
        }
        bills.setCode("200");


        return bills;
    }
}

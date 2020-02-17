package us.gonet.adp.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.adp.algorithms.FourCashUnits;
import us.gonet.adp.business.IDispensingAlg;
import us.gonet.adp.dao.DbServices;
import us.gonet.adp.model.BillsModel;
import us.gonet.adp.validate.Validation;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Casetero;

import java.util.List;
import java.util.Map;

@Component("dispAlg")
public class DispensingAlgImpl implements IDispensingAlg {

    @Autowired
    Validation validation;

    @Autowired
    FourCashUnits fourCashUnits;

    @Autowired
    DbServices dbServices;

    @Override
    public ResponseWrapper dispenseFourUnits(String ip, int monto) throws ServerException {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        List<Casetero> caseteroList = null;
        try {
            caseteroList = dbServices.getCaseteros(ip);
            responseWrapper.setCode("00");
            responseWrapper.addBody(new BillsModel(fourCashUnits.getBills(monto, validation.validateCashUnits( caseteroList), caseteroList)));
        } catch (ServerException e) {
            List<ErrorWS> errorWS = e.getErrors();
            throw new ServerException("ADP-01", errorWS);
        }
        return responseWrapper;
    }

    @Override
    public ResponseWrapper<Casetero> getMinAmount(String ip) throws ServerException {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        List<Casetero> caseteroList = null;
        try {
            int multiplo = 0;
            caseteroList = dbServices.getCaseteros(ip);
            List<Map<String,Object>> cajero = validation.validateCashUnits(caseteroList);
            for(Map casetero : cajero)
            {
                if((boolean)casetero.get("estado"))
                {
                    multiplo =(int) casetero.get("denominacion");
                    break;
                }
            }
            responseWrapper.setCode("00");
            Casetero casetero = new Casetero();
            casetero.setDenominacion(multiplo);
            responseWrapper.addBody(casetero);
        } catch (ServerException e) {
            List<ErrorWS> errorWS = e.getErrors();
            throw new ServerException("ADP-01", errorWS);
        }
        return responseWrapper;
    }


}

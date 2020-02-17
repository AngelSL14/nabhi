package us.gonet.adp.business;

import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Casetero;

public interface IDispensingAlg {
    ResponseWrapper dispenseFourUnits(String ip, int monto) throws ServerException;
    ResponseWrapper<Casetero> getMinAmount(String ip) throws ServerException;

}

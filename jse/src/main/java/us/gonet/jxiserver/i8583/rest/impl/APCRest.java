package us.gonet.jxiserver.i8583.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.iso8583.constants.atm.TranCodes;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.i8583.rest.IRest;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.ATD;
import us.gonet.serverutils.model.jdb.Apc;

@Component( "apcRest" )
public class APCRest implements IRest {

    @Autowired
    private RestRequestFactory restRequestFactory;

    @Override
    public Apc getAPC ( String key ) throws ServerException {
        String service = "/apc/{key}";
        ResponseWrapper< Apc > response = restRequestFactory.buildRestRequest(service, Apc.class, "jdb",
                "No se encontro Apc", "get", "", key);
        return response.getBody().get( 0 );
    }

    @Override
    public us.gonet.serverutils.model.ATMResponseModel sendTransaction(TranCodes tranCode, us.gonet.serverutils.model.ATMRequestModel atmRequestModel) throws ServerException {
        return null;
    }


    @Override
    public ATD getATD ( String ip ) throws ServerException {
        return null;
    }


}

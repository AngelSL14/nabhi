package us.gonet.jxiserver.i8583.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.iso8583.constants.atm.TranCodes;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.i8583.rest.IRest;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.serverutils.model.jdb.ATD;
import us.gonet.serverutils.model.jdb.Apc;

@Component( "atdRest" )
public class ATDRest implements IRest {

    @Autowired
    private RestRequestFactory restRequestFactory;

    public ATD getATD ( String ip ) throws ServerException {

        String service = "/atd/{ip}";
        ResponseWrapper< ATD > response = restRequestFactory.buildRestRequest(service, ATD.class, "jdb",
                "No se encontro Atd", "get", "", ip);
        return response.getBody().get( 0 );
    }

    @Override
    public Apc getAPC ( String key ) throws ServerException {
        return null;
    }

    @Override
    public ATMResponseModel sendTransaction(TranCodes tranCode, ATMRequestModel atmRequestModel) throws ServerException {
        return null;
    }




}

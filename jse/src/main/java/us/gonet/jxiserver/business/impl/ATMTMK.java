package us.gonet.jxiserver.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.business.IATMTMK;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.model.Tmk;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.security.auth.AuthIn;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.ATD;
import us.gonet.serverutils.model.jke.TmkEntity;

import java.sql.Timestamp;

@Component("atmtmk")
public class ATMTMK  implements IATMTMK {

    private static final String KEY_TMK = "MTVK";

    @Autowired
    AuthIn authIn;
    @Autowired
    RestRequestFactory restRequestFactory;

    @Override
    public ResponseWrapper<TmkEntity> atmtmkRequest(Generic atmIp){
        ResponseWrapper<TmkEntity> jkeResponse = new ResponseWrapper<>();
        try {
            String service ="".concat("/atd").concat("/").concat("{ip}");
            ResponseWrapper<ATD> atdResponse = restRequestFactory.buildRestRequest(service, ATD.class, "jdb",
                    "No se encontro ATD: "+atmIp.getIp(), "get",
                    "", atmIp.getIp());
            ATD entity = atdResponse.getBody().get(0);
            service = "".concat("/sock").concat("/sendMessageTPK");
            Tmk tmk = new Tmk();
            tmk.setTermType(entity.getDeviceType());
            tmk.setAtmRemote(entity.getTerminalId());
            tmk.setAtmLocal(KEY_TMK);
            jkeResponse = restRequestFactory.buildRestRequest(service, TmkEntity.class,
            "jke", "No se puede obtener la tpk"+tmk.toString() ,
                    "post", tmk, null);
            jkeResponse.setCode("00");
            jkeResponse.getBody().get(0).setError(String.valueOf(new Timestamp(System.currentTimeMillis()).getTime()/1000L));
            jkeResponse.getBody().get(0).setSequence(atdResponse.getBody().get(0).getSequence());
        } catch (ServerException e) {
            jkeResponse.setCode("500");
            jkeResponse.addError(new ErrorWS("-JXI17", e.getMessage()));
            jkeResponse.addAllError(e.getErrors());
        }

        return jkeResponse;


    }
}
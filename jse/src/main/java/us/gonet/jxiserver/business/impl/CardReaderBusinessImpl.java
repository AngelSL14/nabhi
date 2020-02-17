package us.gonet.jxiserver.business.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.business.ICardReaderBusiness;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.model.StylesBank;
import us.gonet.jxiserver.model.request.AtmInfo;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.jxiserver.model.response.TarjetaInfo;
import us.gonet.security.auth.AuthIn;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.BankStyle;
import us.gonet.serverutils.model.jdb.BinModel;
import us.gonet.serverutils.model.jdb.IDF;

@Component("crdrdrBus")
public class CardReaderBusinessImpl implements ICardReaderBusiness
{
    private static final Logger LOG = LoggerFactory.getLogger(CardReaderBusinessImpl.class);
    @Autowired
    RestRequestFactory restRequestFactory;

    @Autowired
    AuthIn authIn;

    private static final String STYLE_SERVICE = "/bankStyle";

    @Override
    public ResponseWrapper<Generic> incomingCard(Generic generic) {
        ResponseWrapper<Generic> response = new ResponseWrapper<>();

        response.setCode("00");
        return response;
    }

    @Override
    public ResponseWrapper<TarjetaInfo> validatingCard(AtmInfo atmInfo) {
        ResponseWrapper<TarjetaInfo> response = new ResponseWrapper<>();
        if(atmInfo.getTrack().length()<16 || atmInfo.getTrack() == null){
                response.setCode("-203");
                response.addError(new ErrorWS("-JXI20","Datos incompletos"));
        }else{
            String prefix = atmInfo.getTrack().substring(0,8);

            String service = "".concat("/bin").concat("/").concat("{prefix}");
            try {
                ResponseWrapper<BinModel> binResponse = restRequestFactory.buildRestRequest(service, BinModel.class,
                        "jdb", "No se encontro bin: "+prefix, "get",
                        "", prefix);

                String fiid = binResponse.getBody().get(0).getIdf().getFiid();
                service = "".concat("/idf").concat("/").concat("{fiid}");
                ResponseWrapper<IDF> idfResponse = restRequestFactory.buildRestRequest(service, IDF.class,
                        "jdb", "No se encontro idf: "+fiid, "get",
                        "", fiid);

                response = cardResponse(idfResponse.getBody().get(0));
            } catch (ServerException e) {
                try {
                    service = "".concat(STYLE_SERVICE).concat("/").concat("{bank}");
                    ResponseWrapper<BankStyle> bankStyleResponse = restRequestFactory.buildRestRequest(service,
                            BankStyle.class, "jdb", "No se encontro estilo", "get",
                            "", "Prosa S.A.");

                    BankStyle bankStyle = bankStyleResponse.getBody().get(0);
                    response.setCode("200");
                    response.addBody(new TarjetaInfo(bankStyle.getId(), "Tarjeta de CHIP",
                            new StylesBank(bankStyle.getButtons(), bankStyle.getDashboard(), bankStyle.getSections())));
                } catch (ServerException e1) {
                    response.setCode("-203");
                    response.addError(new ErrorWS("-JXI21",e1.getMessage()));
                }
            }
        }
        return response;
    }

    private ResponseWrapper<TarjetaInfo> cardResponse(IDF entity){
        ResponseWrapper<TarjetaInfo> response = new ResponseWrapper<>();
        String service = "".concat(STYLE_SERVICE).concat("/").concat("{idf}");
        ResponseWrapper<BankStyle> bankStyleResponse = new ResponseWrapper<>();
        try {
            bankStyleResponse = restRequestFactory.buildRestRequest(service,
                    BankStyle.class, "jdb", "No se encontro estilo: "+entity.getName(),
                    "get", "", entity.getName());
        } catch (ServerException e) {
            try {
                service = "".concat(STYLE_SERVICE).concat("/").concat("{bank}");
                bankStyleResponse = restRequestFactory.buildRestRequest(service,
                        BankStyle.class, "jdb", "No se encontro estilo: "+entity.getName(),
                        "get", "", "Prosa S.A.");
            } catch (ServerException e1) {
                LOG.error("Error en la peticion entre servicios");

            }
        }
        BankStyle bankStyle = bankStyleResponse.getBody().get(0);
        response.setCode("200");
        response.addBody(new TarjetaInfo(bankStyle.getId(), "Tarjeta de CHIP",
                new StylesBank(bankStyle.getButtons(), bankStyle.getDashboard(), bankStyle.getSections())));
        return response;
    }

}

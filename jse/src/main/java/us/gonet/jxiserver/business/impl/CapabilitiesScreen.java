package us.gonet.jxiserver.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.business.ICapabilitiesScreen;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.jxiserver.model.response.ScreenCapabilities;
import us.gonet.security.auth.AuthIn;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.BankStyle;
import us.gonet.serverutils.model.jdb.Button;
import us.gonet.serverutils.model.jdb.Screen;


@Component("capabilities")
public class CapabilitiesScreen implements ICapabilitiesScreen {

    @Autowired
    RestRequestFactory restRequestFactory;

    @Autowired
    AuthIn authIn;
    @Override
    public ResponseWrapper<ScreenCapabilities> getButtons(Generic generic ) {
        String buttonService = "".concat("/buttons").concat("/").concat("{ip}");

        ResponseWrapper<ScreenCapabilities> rw = new ResponseWrapper<>();
        try {
            ResponseWrapper<Button> resp = restRequestFactory.buildRestRequest(buttonService,
                    Button.class, "jdb", "No se econtraron botones: "+generic.getIp(),
                    "get", "", generic.getIp());
            for(Button b : resp.getBody())
            {
                ScreenCapabilities model = new ScreenCapabilities();
                model.setActiveFDKs(b.getActiveFdk());
                model.setScreen(b.getScreen());
                rw.addBody(model);
            }

            rw.setCode("200");
        } catch (ServerException e) {
            rw.setCode("-500");
            rw.addAllError(e.getErrors());
        }
        return rw;
    }

    @Override
    public ResponseWrapper<Screen> getPublicity(Generic generic) {
        String buttonService = "".concat("/screen").concat("/").concat("{ip}");
        ResponseWrapper<Screen> rw = new ResponseWrapper<>();
        try {
            rw = restRequestFactory.buildRestRequest(buttonService,
                    Screen.class, "jdb", "No se econtro publicidad: "+generic.getIp(),
                    "get", "", generic.getIp());
            buttonService = "".concat("/bankStyle").concat("{bank}");
            ResponseWrapper<BankStyle> responseBankStyle = restRequestFactory.buildRestRequest(buttonService,
                    BankStyle.class, "jdb", "No se encontro estilo del banco", "get",
                    "", "/Prosa S.A.");
            rw.getBody().get(0).setBankStyle(responseBankStyle.getBody().get(0));
            rw.setCode("200");
        } catch (ServerException e) {
            rw.setCode("-500");
            rw.addAllError(e.getErrors());
        }
        return rw;
    }
}

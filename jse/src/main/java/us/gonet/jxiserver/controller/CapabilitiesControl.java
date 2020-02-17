package us.gonet.jxiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.business.ICapabilitiesScreen;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.jxiserver.model.response.ScreenCapabilities;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.jdb.Screen;

@RestController
@RequestMapping("capa")
@CrossOrigin(origins = {"*"} ,maxAge = 6000)
public class CapabilitiesControl {
    @Autowired
    @Qualifier("capabilities")
    ICapabilitiesScreen iCapabilitiesScreen;

    private static final String[] ALLOWED_FIELDS = new String[] { "ip"};

    @InitBinder( "Generic" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping("/bilities")
    public ResponseWrapper<ScreenCapabilities> returnCapabilities(@RequestBody Generic generic){
        return iCapabilitiesScreen.getButtons(generic);
    }

    @ResponseBody
    @PostMapping("/publicity")
    public ResponseWrapper<Screen> returnPublicity(@RequestBody Generic generic){
        return iCapabilitiesScreen.getPublicity(generic);
    }
}

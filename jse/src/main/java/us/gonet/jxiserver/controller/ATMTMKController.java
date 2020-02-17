package us.gonet.jxiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.business.IATMTMK;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.jke.TmkEntity;

@RestController
@RequestMapping("tmkkey")
@CrossOrigin(origins = {"*"},maxAge = 6000)
public class ATMTMKController {

    @Autowired
    @Qualifier("atmtmk")

    IATMTMK atmtmk;
    private static final String[] ALLOWED_FIELDS = new String[] { "ip"};

    @InitBinder( "Generic" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping("/atmData")
    public ResponseWrapper<TmkEntity> incomingAtm(@RequestBody Generic atm){
        return atmtmk.atmtmkRequest(atm);//Vente Cuau Vente en Roman, va a quedar bien blanco
    }
}

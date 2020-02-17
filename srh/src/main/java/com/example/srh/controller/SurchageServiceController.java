package com.example.srh.controller;

import com.example.srh.bussines.ISurchageService;
import com.example.srh.exeptionsutils.ResponseWrapper;
import com.example.srh.models.RequestSurchage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.serverutils.model.jdb.SurchargeModel;

@RestController
@RequestMapping ("surchage")
@CrossOrigin ( origins = { "*" } , maxAge = 6000)
public class SurchageServiceController {

    @Autowired
    @Qualifier("ISurchageService")
    ISurchageService sur;

    private static final String[] ALLOWED_FIELDS = new String[] { "track", "ip", "transactionCode" };

    @InitBinder( "ATMRequestModel" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }


    @ResponseBody
    @PostMapping("/sendSurchage")
    public ResponseWrapper<SurchargeModel> sendSurchage (@RequestBody RequestSurchage s ){
            return sur.sendSurchage( s );
    }
}

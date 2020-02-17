package com.dksits.jke.controller;

import com.dksits.jke.business.ISocketServices;
import com.dksits.jke.core.message.Response;
import com.dksits.jke.model.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;


@RestController
@RequestMapping ( "sock" )
@CrossOrigin ( origins = { "*" } )
@Api ( tags = "JKE Services", value = "JKE Services" )
public class ServicesController {


    private static final String[] DISALLOWED_FIELDS = new String[] { "details.role", "details.age",
            "is_admin" };

    @InitBinder( "Request" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( DISALLOWED_FIELDS );
    }


    @Autowired
    @Qualifier ( "jkeser" )
    ISocketServices bussines;

    @ResponseBody
    @PostMapping ( "/onSocket" )
    public ResponseWrapper onSocket ( @RequestBody ServiceInfo ser ) {
        return bussines.onSocket( ser );
    }

    @ResponseBody
    @PostMapping ( "/offSocket" )
    public ResponseWrapper offSocket ( @RequestBody ServiceInfo ser ) {
        return bussines.offSocket( ser );
    }

    @ResponseBody
    @PostMapping ( "/socketStatus" )
    public ResponseWrapper socketStatus ( @RequestBody ServiceInfo ser ) {
        return bussines.statusSocket( ser );
    }

    @ResponseBody
    @PostMapping ( "/sendMessage" )
    public Response sendMessage ( @RequestBody TMK message ) {
        return bussines.sendMessage( message );
    }

    @ResponseBody
    @PostMapping ( "/sendMessageTPK" )
    public ResponseWrapper< TmkEntity > sendMessageTPK ( @RequestBody TPK message ) {
        return bussines.sendMessageTPK( message );
    }

    @ResponseBody
    @PostMapping ( "/sendMessagePinBlock" )
    public ResponseWrapper< String > sendMessagePinBlock ( @RequestBody PinBlock message ) {
        return bussines.sendMessagePinBlock( message );
    }
}
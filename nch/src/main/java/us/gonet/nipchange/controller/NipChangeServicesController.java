package us.gonet.nipchange.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.nipchange.business.INipChangeServices;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;

@RestController
@RequestMapping ( "nch" )
@CrossOrigin ( origins = { "*" }, maxAge = 6000 )
@Api ( tags = "Nip Change Services", value = "Nip Change Services" )
public class NipChangeServicesController {

    @Autowired
    @Qualifier ( "nipChangeServices" )
    INipChangeServices business;

    private static final String[] DISALLOWED_FIELDS = new String[] { "details.role", "details.age",
            "is_admin" };

    @InitBinder( "ATMRequestModel" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( DISALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping ( "/sendNipChange" )
    public ResponseWrapper sendNipChange( @RequestBody ATMRequestModel atmRequestModel ) {
        return business.sendNipChange( atmRequestModel );
    }
}

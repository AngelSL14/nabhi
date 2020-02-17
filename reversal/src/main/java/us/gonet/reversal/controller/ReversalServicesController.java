package us.gonet.reversal.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.reversal.business.IReversalServices;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.serverutils.model.reversal.ATMReversalModel;

@RestController
@RequestMapping ( "rev" )
@CrossOrigin ( origins = { "*" }, maxAge = 6000 )
@Api ( tags = "Reversal Services", value = "Reversal Services" )
public class ReversalServicesController {

    @Autowired
    @Qualifier ( "reversalServices" )
    IReversalServices business;

    private static final String[] DISALLOWED_FIELDS = new String[] { "details.role", "details.age",
            "is_admin" };

    @InitBinder( "ATMReversalModel" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( DISALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping ( "/sendReversal" )
    public ResponseWrapper< ATMResponseModel > sendReversal ( @RequestBody ATMReversalModel arm ) {
        return business.sendReversal( arm );
    }
}

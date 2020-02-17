package us.gonet.rcpt.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.rcpt.business.IRCPTServices;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.RCPTRequestModel;
import us.gonet.serverutils.model.receipt.Receipt;

@RestController
@RequestMapping ( "rcpt" )
@CrossOrigin ( origins = { "*" }, maxAge = 6000 )
@Api ( tags = "Receipt Services", value = "Receipt Services" )
public class RCPTController {

    @Autowired
    @Qualifier ( "rcptServices" )
    IRCPTServices business;

    private static final String[] DISALLOWED_FIELDS = new String[] { "details.role", "details.age",
            "is_admin" };

    @InitBinder( "RCPTRequestModel" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( DISALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping ( "/createRCPT" )
    public ResponseWrapper< Receipt > createRCPT ( @RequestBody RCPTRequestModel rcptRequestModel ) {
        return business.createRCPT( rcptRequestModel );
    }
}

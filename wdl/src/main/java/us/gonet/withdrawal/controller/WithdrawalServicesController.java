package us.gonet.withdrawal.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.withdrawal.business.IWithdrawalServices;

@RestController
@RequestMapping ( "wdl" )
@CrossOrigin ( origins = { "*" }, maxAge = 6000 )
@Api ( tags = "Withdrawal Services", value = "Withdrawal Services" )
public class WithdrawalServicesController {

    @Autowired
    @Qualifier ( "withdrawalServices" )
    IWithdrawalServices business;

    private static final String[] DISALLOWED_FIELDS = new String[] { "details.role", "details.age",
            "is_admin" };

    @InitBinder( "ATMRequestModel" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( DISALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping ( "/sendWithdrawal" )
    public ResponseWrapper sendWithdrawal ( @RequestBody ATMRequestModel atmRequestModel ) {
        return business.sendWithdrawal( atmRequestModel );
    }
}

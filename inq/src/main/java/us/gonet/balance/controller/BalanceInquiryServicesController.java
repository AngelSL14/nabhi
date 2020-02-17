package us.gonet.balance.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.balance.business.IBalanceInquiryServices;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;

@RestController
@RequestMapping ( "inq" )
@CrossOrigin ( origins = { "*" }, maxAge = 6000 )
@Api ( tags = "Inquiry Services", value = "Inquiry Services" )
public class BalanceInquiryServicesController {

    @Autowired
    @Qualifier ( "balanceInquiryServices" )
    IBalanceInquiryServices business;

    private static final String[] DISALLOWED_FIELDS = new String[] { "details.role", "details.age",
            "is_admin" };

    @InitBinder( "ATMRequestModel" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( DISALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping ( "/sendBalanceInquiry" )
    public ResponseWrapper sendBalanceInquiry( @RequestBody ATMRequestModel atmRequestModel ) {
        return business.sendBalanceInquiry( atmRequestModel );
    }
}

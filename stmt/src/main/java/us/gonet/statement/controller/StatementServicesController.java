package us.gonet.statement.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.statement.business.IStatementServices;

@RestController
@RequestMapping ( "stmt" )
@CrossOrigin ( origins = { "*" }, maxAge = 6000 )
@Api ( tags = "Statement Services", value = "Statement Services" )
public class StatementServicesController {

    @Autowired
    @Qualifier ( "statementServices" )
    IStatementServices business;

    private static final String[] DISALLOWED_FIELDS = new String[] { "details.role", "details.age",
            "is_admin" };

    @InitBinder( "ATMRequestModel" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( DISALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping ( "/sendStatement" )
    public ResponseWrapper sendStatement( @RequestBody ATMRequestModel atmRequestModel ) {
        return business.sendStatement( atmRequestModel );
    }
}

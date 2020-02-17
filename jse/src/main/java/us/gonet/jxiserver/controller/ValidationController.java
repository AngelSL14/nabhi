package us.gonet.jxiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.business.IValidationBusiness;
import us.gonet.jxiserver.model.request.CashWithdrawalModel;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.model.jdb.BillsModel;
import us.gonet.serverutils.model.jdb.Casetero;

@RestController
@RequestMapping("vldtn")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class ValidationController {

    @Autowired @Qualifier("validationBus")
    IValidationBusiness business;

    private static final String[] ALLOWED_FIELDS = new String[] { "ip", "cashWithAmount"};

    @InitBinder( "Generic" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @PostMapping("wthdw")
    @ResponseBody
    public ResponseWrapper<BillsModel> validateWithdrawal(@RequestBody CashWithdrawalModel generic)
    {
        return business.validateWithdrawal(generic);
    }

    @PostMapping("mntMnm")
    @ResponseBody
    public ResponseWrapper<Casetero> validateMinAmount(@RequestBody Generic generic)
    {
        return business.validateMinAmount(generic);

    }


}

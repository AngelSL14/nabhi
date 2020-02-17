package us.gonet.jxiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.business.IPrinterBusiness;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.jxiserver.model.response.Ticket;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;

@RestController
@RequestMapping("prntrcntrllr")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class PrinterController {
    @Autowired
    @Qualifier("prntrBus")
    IPrinterBusiness business;

    private static final String[] ALLOWED_FIELDS = new String[] { "ip", "termId"};

    @InitBinder( "Generic" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }


    @ResponseBody
    @PostMapping("/prntng")
    public ResponseWrapper<Ticket> printingTicket(@RequestBody Generic generic) {
        ResponseWrapper<Ticket> response = new ResponseWrapper<>();
        response.setCode("200");
        response.addBody(business.printingTicket(generic));
        return response;
    }
}
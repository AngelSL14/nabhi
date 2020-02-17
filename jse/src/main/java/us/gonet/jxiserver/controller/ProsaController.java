package us.gonet.jxiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.business.IProsaBusiness;
import us.gonet.jxiserver.config.Properties;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.dao.journal.JournalWritter;
import us.gonet.jxiserver.i8583.manager.TransactionManager;
import us.gonet.jxiserver.model.request.*;
import us.gonet.jxiserver.model.response.GenericProcess;
import us.gonet.jxiserver.utils.DestinationAccount;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.BillsModel;

import java.util.Map;

@RestController
@RequestMapping("athz")
@CrossOrigin(origins = { "*" }, maxAge = 6000)

public class ProsaController {

    @Autowired
    @Qualifier("prsBus")
    IProsaBusiness business;

    @Autowired
    TransactionManager transactionManager;

    @Autowired @Qualifier("props")
    Properties properties;

    @Autowired
    RestRequestFactory restRequestFactory;

    @Autowired
    JournalWritter journalWritter;

    private static final String TYPE = "Tipo de cuenta: ";
    private static final String ACCEPT_COMMISION = "El cliente acepta la comision por ";

    private static final String[] ALLOWED_FIELDS = new String[] { "termId", "ip", "txCommission",
    "tipoCuenta", "nip", "track", "emv", "cashWithAmount", "newPin", "confirmNewPin", "telefono", "company"};

    @InitBinder( "Generic" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping("/wthdw")
    public ResponseWrapper<BillsModel> cashWithAuth (@RequestBody CashWithdrawalModel generic)
    {
        journalWritter.writeJournal(generic.getTermId(), TYPE+ DestinationAccount.getTypeFromCode(generic.getTipoCuenta()));
        journalWritter.writeJournal(generic.getTermId(), "Operacion retiro de efectivo");
        journalWritter.writeJournal(generic.getTermId(), ACCEPT_COMMISION+generic.getTxCommission());
        ResponseWrapper<BillsModel> response = new ResponseWrapper<>();
        try {
            response = business.cashWithAuth(generic);
        } catch (ServerException e) {
            response.setCode("-500");
            response.addAllError(e.getErrors());
        }
        return  response;
    }
    @ResponseBody
    @PostMapping("/blnInq")
    public ResponseWrapper<Map<String, String>> balInquiryAuth (@RequestBody Generic generic)
    {
        journalWritter.writeJournal(generic.getTermId(), TYPE+ DestinationAccount.getTypeFromCode(generic.getTipoCuenta()));
        journalWritter.writeJournal(generic.getTermId(), "Operacion consulta de saldo");
        journalWritter.writeJournal(generic.getTermId(), ACCEPT_COMMISION+generic.getTxCommission());

        ResponseWrapper<Map<String, String>> response = new ResponseWrapper<>();
        try {
            response = business.balInquiryAuth(generic);
        } catch (ServerException e) {
            response.setCode("-500");
            response.addAllError(e.getErrors());
        }

        return response;

    }

    @ResponseBody
    @PostMapping("/cmmssn")
    public ResponseWrapper<Generic> getCommission (@RequestBody AtmInfo atmInfo)
    {
        ResponseWrapper<Generic> response = new ResponseWrapper<>();
        try {
            response = business.getCommission(atmInfo);
        } catch (ServerException e) {
            response.setCode("-500");
            response.addAllError(e.getErrors());
        }
        return  response;
    }

    @ResponseBody
    @PostMapping("/lstTrx")
    public ResponseWrapper<String> listTrx(@RequestBody Generic generic)
    {
        journalWritter.writeJournal(generic.getTermId(), TYPE+ DestinationAccount.getTypeFromCode(generic.getTipoCuenta()));
        journalWritter.writeJournal(generic.getTermId(), "Operacion listado de movimientos");
        journalWritter.writeJournal(generic.getTermId(), ACCEPT_COMMISION+generic.getTxCommission());
        ResponseWrapper<String> response = new ResponseWrapper<>();
        try {
            response = business.listTrx(generic);
        } catch (ServerException e) {
            response.setCode("-500");
            response.addAllError(e.getErrors());
        }
        return  response;
    }

    @ResponseBody
    @PostMapping("/chngPNb")
    public ResponseWrapper<GenericProcess> changeNip(@RequestBody ChangeNipModel generic)
    {
        journalWritter.writeJournal(generic.getTermId(), TYPE+ DestinationAccount.getTypeFromCode(generic.getTipoCuenta()));
        journalWritter.writeJournal(generic.getTermId(), "Operacion cambio de nip");
        journalWritter.writeJournal(generic.getTermId(), ACCEPT_COMMISION+generic.getTxCommission());
        ResponseWrapper<GenericProcess> response = new ResponseWrapper<>();
        try {
            response = business.changePin(generic);
        } catch (ServerException e) {
            response.setCode("-500");
            response.addAllError(e.getErrors());
        }
        return  response;
    }

    @ResponseBody
    @PostMapping("/gnrcSl")
    public ResponseWrapper<GenericProcess> genericSale(@RequestBody GenericSaleModel generic)
    {
        journalWritter.writeJournal(generic.getTermId(), TYPE+ DestinationAccount.getTypeFromCode(generic.getTipoCuenta()));
        journalWritter.writeJournal(generic.getTermId(), "Operacion compra de tiempo aire");
        journalWritter.writeJournal(generic.getTermId(), ACCEPT_COMMISION+generic.getTxCommission());
        ResponseWrapper<GenericProcess> response = new ResponseWrapper<>();
        try {
            response = business.genericSale(generic);
        } catch (ServerException e) {
            response.setCode("-500");
            response.addAllError(e.getErrors());
        }
        return  response;
    }
}

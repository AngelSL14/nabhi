package us.gonet.jxiserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.business.ICardReaderBusiness;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.dao.journal.JournalWritter;
import us.gonet.jxiserver.model.request.AtmInfo;
import us.gonet.jxiserver.model.response.TarjetaInfo;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Atm;

@RestController
@RequestMapping("crdrdr")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class CardReaderController {

    @Autowired
    private RestRequestFactory restRequestFactory;

    @Autowired
    @Qualifier("crdrdrBus")
    private ICardReaderBusiness business;

    @Autowired
    private JournalWritter journalWritter;

    private static final String[] ALLOWED_FIELDS = new String[] { "ip", "track", "termId"};

    @InitBinder( "AtmInfo" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }



    @ResponseBody
    @PostMapping("/crdincmg")
    public  boolean cajeroVerificacion (@RequestBody AtmInfo generic)
    {
        boolean status = false;
        String atmService ="".concat("/atm").concat("/").concat("{ip}");
        try {
            restRequestFactory.buildRestRequest(atmService, Atm.class, "jdb",
                    "No se encontro cajero con el parametro: "+generic.getIp(),
                    "get", null, generic.getIp());
            status = true;
        } catch (ServerException e) {
            status = false;
        }
        return status;

    }

    @ResponseBody
    @PostMapping("/crdvldt")
    public ResponseWrapper<TarjetaInfo>  validateCard(@RequestBody AtmInfo atmInfo)
    {
        journalWritter.writeJournal(atmInfo.getTermId(), "Tarjeta introducida. Inicia transaccion");
        return business.validatingCard(atmInfo);
    }

    @ResponseBody
    @PostMapping("/crdrmvd")
    public void cardRemoved(@RequestBody AtmInfo atmInfo)
    {
        journalWritter.writeJournal(atmInfo.getTermId(), "Tarjeta retirada. Termina transaccion");
    }
}

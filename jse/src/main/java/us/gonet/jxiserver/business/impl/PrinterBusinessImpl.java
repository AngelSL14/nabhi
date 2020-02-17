package us.gonet.jxiserver.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.business.IPrinterBusiness;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.dao.journal.JournalWritter;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.jxiserver.model.response.Ticket;
import us.gonet.security.auth.AuthIn;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Atm;

@Component("prntrBus")
public class PrinterBusinessImpl implements IPrinterBusiness {

    @Autowired
    RestRequestFactory restRequestFactory;

    @Autowired
    AuthIn authIn;

    @Autowired
    JournalWritter journalWritter;

    @Override
    @SuppressWarnings( "unchecked" )
    public Ticket printingTicket(Generic generic)
    {
        Ticket printingTicket = new Ticket();
        try {
            ResponseWrapper<Atm> atm = restRequestFactory.buildRestRequest("/atm/{ip}", Atm.class,
                    "jdb", "No se encontro atm: "+generic.getIp(), "get",
                    "", generic.getIp());
            printingTicket.setBodyTicket(atm.getBody().get(0).getReceipt());
            printingTicket.setCode("00");
            journalWritter.writeJournal(generic.getTermId(), atm.getBody().get(0).getReceipt());
        } catch (ServerException e) {
            printingTicket.setCode("-500");
            printingTicket.setBodyTicket("");
        }

        return printingTicket;
    }
}

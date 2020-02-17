package us.gonet.jxiserver.reversal;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.iso8583.constants.ReversalCodes;
import us.gonet.jxiserver.dao.RestRequestFactory;
import us.gonet.jxiserver.dao.journal.JournalWritter;
import us.gonet.jxiserver.model.request.AtmNotificationModel;
import us.gonet.security.auth.AuthIn;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.serverutils.model.jdb.Atm;
import us.gonet.serverutils.model.reversal.ATMReversalModel;

@Component("reversal")
public class Reversal {
    @Autowired
    RestRequestFactory restRequestFactory;

    @Autowired
    AuthIn authIn;
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Reversal.class);
    @Autowired
    JournalWritter journalWritter;

    public void generateReversal(AtmNotificationModel model, int monto){
        String reverseType = "Reverso Parcial";
        if(monto == 0)
        {
            reverseType = "Reverso Total";
        }
        String service = "/atm/{ip}";
        try {
            ResponseWrapper<Atm> atmResponse = restRequestFactory.buildRestRequest(service, Atm.class, "jdb",
                    "No se encontro el atm", "get", "", model.getIp());
            ATMReversalModel atmReversalModel = new ATMReversalModel();
            atmReversalModel.setMessage(atmResponse.getBody().get(0).getLastTrx());
            atmReversalModel.setDispensedAmount(monto);
            atmReversalModel.setReversalCode(ReversalCodes.SUSPECTED_MALFUNCTION.getValue());
            service = "/rev/sendReversal";
            restRequestFactory.buildRestRequest(service,
                    ATMResponseModel.class, "reversal", "El reverso no fue exitoso",
                    "post", atmReversalModel, null);
            String response = reverseType + " Exitoso ";
            LOG.info(response);
            journalWritter.writeJournal(atmResponse.getBody().get(0).getId(), response);
        } catch (ServerException e) {
            journalWritter.writeJournal(model.getTermId(), "Hubo un error al realizar el "+reverseType + e.getErrors().toString());

        }
    }
}

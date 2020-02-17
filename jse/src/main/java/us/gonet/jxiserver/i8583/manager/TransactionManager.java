package us.gonet.jxiserver.i8583.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.iso8583.constants.atm.FromAccount;
import us.gonet.iso8583.constants.atm.TranCodes;
import us.gonet.jxiserver.i8583.misc.ATMRequestBuilder;
import us.gonet.jxiserver.i8583.rest.IRest;
import us.gonet.jxiserver.model.request.Generic;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.serverutils.model.jdb.ATD;
import us.gonet.serverutils.model.jdb.Apc;


@Component("manager")
public class TransactionManager {

    @Autowired
    @Qualifier( "atdRest" )
    IRest atdRest;

    @Autowired
    @Qualifier( "apcRest" )
    IRest apcRest;

    @Autowired
    @Qualifier( "processingTransaction" )
    IRest processingTransaction;

    @Autowired
    ATMRequestBuilder atmRequestBuilder;

    private static final String CHANNEL_ATM = "01";

    public ATMResponseModel performTransaction(Generic e, String code ) throws ServerException {
        String ip = e.getIp();
        TranCodes tranCode = TranCodes.getValue(code);
        ATD atd;
        Apc apc;
        ATMRequestModel atmRequestModel;
        try {
            atd =  atdRest.getATD( ip );
            apc = apcRest.getAPC( CHANNEL_ATM + tranCode.getValue() + e.getTipoCuenta() + FromAccount.NO_ACCOUNT.getValue() );
            atmRequestModel = atmRequestBuilder.build( e, atd, apc, tranCode.getValue() );
            return processingTransaction.sendTransaction( tranCode, atmRequestModel );
        } catch ( ServerException ex ) {

            throw new ServerException( ex.getMessage(), ex.getErrors() );
        }
    }

}

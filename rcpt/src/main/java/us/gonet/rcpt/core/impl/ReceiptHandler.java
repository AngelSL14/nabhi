package us.gonet.rcpt.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.iso8583.constants.atm.TranCodes;
import us.gonet.rcpt.core.IReceiptBuilder;
import us.gonet.rcpt.core.IReceiptHandler;
import us.gonet.rcpt.core.rest.RCPTRest;
import us.gonet.rcpt.core.utils.RCPTDummy;
import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.jdb.njdb.Rcpt;
import us.gonet.serverutils.model.receipt.Receipt;
import us.gonet.utils.DecodeISO8583;

import java.util.ArrayList;
import java.util.List;

@Component( "receiptHandler" )
public class ReceiptHandler implements IReceiptHandler {

    @Autowired
    @Qualifier( "receiptBuilder" )
    IReceiptBuilder receiptBuilder;

    @Autowired
    RCPTRest rcptRest;

    @Override
    public ResponseWrapper< Receipt > createReceipt( ATMRequestModel ar, String message ){
        List< Receipt > body = new ArrayList <>();
        ResponseWrapper< Receipt > responseWrapper = new ResponseWrapper <>();
        ISO i = new DecodeISO8583( message ).getIso();
        Rcpt rcpt;
        Receipt receipt;
        TranCodes tranCode = TranCodes.getValue( i.getDataElements().get( 2 ).getContentField().substring( 0, 2 ) );
        String key = ar.getTermFiid() + tranCode.getValue();
        try {
            rcpt = rcptRest.getScriptReceipt( key );
        }catch ( ServerException e ){
            key = "****" + tranCode.getValue();
            try {
                rcpt = rcptRest.getScriptReceipt( key );
            } catch ( ServerException e1 ) {
                rcpt = RCPTDummy.getScripRcpt( key );
            }
        }
        try {
            receipt = receiptBuilder.build( ar, i, rcpt.getHeader(), rcpt.getBody(), rcpt.getTrailer() );
        } catch ( ServerException e ) {
            responseWrapper.setCode( "96" );
            responseWrapper.addAllError( e.getErrors() );
            responseWrapper.setBody( body );
            return responseWrapper;
        }
        responseWrapper.setCode( "00" );
        responseWrapper.addBody( receipt );
        return responseWrapper;
    }
}

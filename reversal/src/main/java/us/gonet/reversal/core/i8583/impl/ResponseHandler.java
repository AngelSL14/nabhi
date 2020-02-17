package us.gonet.reversal.core.i8583.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import us.gonet.iso8583.constants.MessageTypes;
import us.gonet.iso8583.constants.ReversalCodes;
import us.gonet.iso8583.constants.atm.ResponseCodes;
import us.gonet.iso8583.constants.atm.TranCodes;
import us.gonet.iso8583.message.Reversal0420;
import us.gonet.r8583.rest.IRest;
import us.gonet.r8583.rest.impl.isoservices.SendISOMessage;
import us.gonet.r8583.rest.impl.rcpt.ReceiptIO;
import us.gonet.reversal.core.i8583.IResponseHandler;
import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.serverutils.model.reversal.ATMReversalModel;
import us.gonet.utils.DecodeISO8583;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Import ( { SendISOMessage.class, ReceiptIO.class } )
@Component( "responseHandler" )
public class ResponseHandler implements IResponseHandler {

    private static final Logger LOG = LoggerFactory.getLogger( ResponseHandler.class );
    private MessageTypes response = MessageTypes.FINALCIAL_RESPONSE_MSG;
    private TranCodes withdrawal = TranCodes.WITHDRAWAL;
    private ResponseCodes approved = ResponseCodes.APPROVED;

    @Autowired
    @Qualifier( "sendISOMessage" )
    IRest sendISOMessage;

    @Override
    public ResponseWrapper< ATMResponseModel > sendMessage( ATMReversalModel arm ){
        ResponseWrapper< ATMResponseModel > responseWrapper = new ResponseWrapper <>();
        try {
            ISO i = new DecodeISO8583( arm.getMessage() ).getIso();
            if ( !i.getHeader().get( 6 ).getContentField().equals( response.getValue() ) ){
                List< ErrorWS > errors = new ArrayList <>();
                ErrorWS error = new ErrorWS( "REV-01", "Invalid message type" );
                errors.add( error );
                responseWrapper.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
                responseWrapper.setErrorWS( errors );
                return responseWrapper;
            }
            TranCodes tranCode = TranCodes.getValue( i.getDataElements().get( 2 ).getContentField().substring( 0, 2 ) );
            if ( tranCode != withdrawal ){
                List< ErrorWS > errors = new ArrayList <>();
                ErrorWS error = new ErrorWS( "REV-02", "Invalid tran code, only financial transactions are allowed" );
                errors.add( error );
                responseWrapper.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
                responseWrapper.setErrorWS( errors );
                return responseWrapper;
            }
            if ( !approved.getValue().equals( i.getDataElements().get( 38 ).getContentField() ) ){
                List< ErrorWS > errors = new ArrayList <>();
                ErrorWS error = new ErrorWS( "REV-03", "Only approved transaction reversals are allowed" );
                errors.add( error );
                responseWrapper.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
                responseWrapper.setErrorWS( errors );
                return responseWrapper;
            }

            ReversalCodes reversalCodes = ReversalCodes.RESERVED_U4;
            for ( ReversalCodes r : ReversalCodes.values() ){
                if ( r.getValue().equals( arm.getReversalCode() ) ){
                    reversalCodes = r;
                }
            }
            ISO r0420 = new Reversal0420( i, reversalCodes, arm.getDispensedAmount() ).getIso();
            ISO r0430 = sendISOMessage.sendMessage( r0420 );
            if ( !r0430.isTimeOut() ){
                handlerResponse( r0430, responseWrapper );
                return responseWrapper;
            }else {
                List< ATMResponseModel > body = new ArrayList <>();
                List< ErrorWS > errors = new ArrayList <>();
                ErrorWS error = new ErrorWS( "REV-04", "Response received to late" );
                errors.add( error );
                ATMResponseModel r = new ATMResponseModel();
                r.setReceipt( "" );
                r.setMessage( r0420 );
                body.add( r );
                responseWrapper.setCode( ResponseCodes.ISSUER_INOPERATIVE.getValue() );
                responseWrapper.addAllError( errors );
                responseWrapper.setBody( body );
                return responseWrapper;
            }
        } catch ( ServerException e ) {
            LOG.error( "Error connecting with server" );
            List< ATMResponseModel > body = new ArrayList <>();
            ATMResponseModel r = new ATMResponseModel();
            r.setReceipt( "" );
            r.setMessage( new ISO() );
            body.add( r );
            responseWrapper.setCode( ResponseCodes.SYSTEM_MALFUNCTION.getValue() );
            responseWrapper.addAllError( e.getErrors() );
            responseWrapper.setBody( body );
            return responseWrapper;
        }
    }

    private void handlerResponse ( ISO r0420, ResponseWrapper< ATMResponseModel > responseWrapper ) {
        List< ATMResponseModel > body = new ArrayList <>();
        ATMResponseModel r = new ATMResponseModel();
        responseWrapper.setCode( r0420.getDataElements().get( 38 ).getContentField() );
        body.add( r );
        responseWrapper.setBody( body );
        r.setMessage( r0420 );
    }
}

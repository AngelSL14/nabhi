package us.gonet.withdrawal.core.i8583.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import us.gonet.iso8583.constants.atm.ResponseCodes;
import us.gonet.r8583.rest.IRest;
import us.gonet.r8583.rest.impl.isoservices.SendISOMessage;
import us.gonet.r8583.rest.impl.rcpt.ReceiptIO;
import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.withdrawal.core.i8583.IISOBuilder;
import us.gonet.withdrawal.core.i8583.IResponseHandler;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Import ( { SendISOMessage.class, ReceiptIO.class } )
@Component( "responseHandler" )
public class ResponseHandler implements IResponseHandler {

    private static final Logger LOG = LoggerFactory.getLogger( ResponseHandler.class );

    @Autowired
    @Qualifier ( "iSOBuilder" )
    IISOBuilder isoBuilder;

    @Autowired
    @Qualifier( "sendISOMessage" )
    IRest sendISOMessage;

    @Autowired
    @Qualifier( "receiptIO" )
    IRest receiptIO;

    @Override
    public ResponseWrapper< ATMResponseModel > sendMessage( ATMRequestModel wm ){
        ResponseWrapper< ATMResponseModel > responseWrapper = new ResponseWrapper <>();
        try {
            ISO r0200 = isoBuilder.buildMessage( wm );
            ISO r0210 = sendISOMessage.sendMessage( r0200 );
            if ( !r0210.isTimeOut() ){
                handlerResponse( wm, r0210, responseWrapper );
                return responseWrapper;
            }else {
                List< ATMResponseModel > body = new ArrayList <>();
                List< ErrorWS > errors = new ArrayList <>();
                ErrorWS error = new ErrorWS( "WDL-05", "Response received to late" );
                errors.add( error );
                ATMResponseModel r = new ATMResponseModel();
                r.setReceipt( "" );
                r.setMessage( r0200 );
                body.add( r );
                responseWrapper.setCode( ResponseCodes.ISSUER_INOPERATIVE.getValue() );
                responseWrapper.addAllError( errors );
                responseWrapper.setBody( body );
                return responseWrapper;
            }
        } catch ( ServerException e ) {
            LOG.error( "Error al manejar el mensaje" );
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

    private void handlerResponse ( ATMRequestModel wm, ISO r0210, ResponseWrapper< ATMResponseModel > responseWrapper ) {
        List< ATMResponseModel > body = new ArrayList <>();
        ATMResponseModel r = new ATMResponseModel();
        responseWrapper.setCode( r0210.getDataElements().get( 38 ).getContentField() );
        body.add( r );
        responseWrapper.setBody( body );
        if ( r0210.getDataElements().get( 38 ).getContentField().equals( ResponseCodes.APPROVED.getValue() ) ){
            try {
                r.setReceipt( receiptIO.sendReceiptRequest( wm, r0210.getMessage() ).getTicket() );
            } catch ( ServerException e ) {
                LOG.error( "No se pudo crear el recibo mediante el servicio, se crea de manera local" );
            }
        }
        r.setMessage( r0210 );
    }
}

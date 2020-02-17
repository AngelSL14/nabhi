package us.gonet.r8583.rest.impl.rcpt;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import us.gonet.r8583.rest.IRest;
import us.gonet.r8583.rest.RestServices;
import us.gonet.security.utils.RestUtils;
import us.gonet.serializable.data.ISO;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.RCPTRequestModel;
import us.gonet.serverutils.model.node.NodeSingleModel;
import us.gonet.serverutils.model.receipt.Receipt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component ( "receiptIO" )
public class ReceiptIO implements IRest{

    @Autowired
    RestUtils restUtils;
    @Autowired
    RestServices services;
    private static final String CREATE_RCPT = "/rcpt/createRCPT";
    private static final String ERROR = "Error al crear el recibo";
    private static final Logger LOG = LoggerFactory.getLogger( ReceiptIO.class );

    public Receipt sendReceiptRequest ( ATMRequestModel ar, String message ) throws ServerException {
        RCPTRequestModel rcptRequestModel = new RCPTRequestModel();
        rcptRequestModel.setAtmRequestModel( ar );
        rcptRequestModel.setMessage( message );
        RestTemplate rt = new RestTemplate();
        HttpEntity< RCPTRequestModel > requestEntity = new HttpEntity<>( rcptRequestModel, restUtils.buildHttpHeaders( "rcpt" ));
        ResponseEntity< ResponseWrapper > restResponse;
        try{
            restResponse = rt.exchange( services.getRcptUrl() + CREATE_RCPT, HttpMethod.POST, requestEntity, ResponseWrapper.class );
        }catch ( HttpClientErrorException | HttpServerErrorException e ){
            List< ErrorWS > errors = new ArrayList <>();
            errors.add( new ErrorWS( "API-04", "Servicio RCPT create no disponible" ) );
            throw new ServerException( ERROR , errors );
        }
        ResponseWrapper < Receipt > response =  restResponse.getBody();
        if ( response != null ){
            if ( !response.getBody().isEmpty() ) {
                Receipt receipt;
                ObjectMapper mapper = new ObjectMapper();
                try {
                    byte[] object = mapper.writeValueAsBytes( response.getBody().get( 0 ) );
                    receipt = mapper.readValue( object, Receipt.class );
                } catch ( IOException e ) {
                    LOG.error( "Error al parsear el recibo" );
                    throw new ServerException( ERROR, response.getErrorWS() );
                }
                return receipt;
            } else {
                throw new ServerException( ERROR, response.getErrorWS() );
            }
        }else {
            List< ErrorWS > errors = new ArrayList <>();
            errors.add( new ErrorWS( "API-02", "Invalid Response RCPT" ) );
            throw new ServerException( ERROR, errors );
        }
    }

    @Override
    public String translatePinBlock ( String currentPinBlock, String track2, String termID, String termType, NodeSingleModel ns ) throws ServerException {
        return null;
    }

    @Override
    public ISO sendMessage ( ISO isoRequest ) throws ServerException {
        return null;
    }
}

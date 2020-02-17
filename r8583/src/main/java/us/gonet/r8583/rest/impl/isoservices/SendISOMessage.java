package us.gonet.r8583.rest.impl.isoservices;

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
import us.gonet.serverutils.model.node.NodeSingleModel;
import us.gonet.serverutils.model.receipt.Receipt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Component ( "sendISOMessage" )
public class SendISOMessage implements IRest{

    @Autowired
    RestUtils restUtils;
    @Autowired
    RestServices services;
    private static final String SEND_ISO = "/iso/process";
    private static final String ERROR = "Error al enviar el mensaje ISO-8583";
    private static final Logger LOG = LoggerFactory.getLogger( SendISOMessage.class );


    @Override
    public Receipt sendReceiptRequest ( ATMRequestModel ar, String message ) throws ServerException {
        return null;
    }

    @Override
    public String translatePinBlock ( String currentPinBlock, String track2, String termID, String termType, NodeSingleModel ns ) throws ServerException {
        return null;
    }

    public ISO sendMessage ( ISO isoRequest ) throws ServerException {
        RestTemplate rt = new RestTemplate();
        HttpEntity< ISO > requestEntity = new HttpEntity<>( isoRequest, restUtils.buildHttpHeaders( "iso" ) );
        ResponseEntity< ResponseWrapper  > restResponse;
        try{
            restResponse = rt.exchange( services.getIsoUrl() + SEND_ISO, HttpMethod.POST, requestEntity, ResponseWrapper.class );
        }catch ( HttpClientErrorException | HttpServerErrorException e ){
            List< ErrorWS > errors = new ArrayList <>();
            errors.add( new ErrorWS( "API-03", ERROR ) );
            throw new ServerException( ERROR , errors );
        }
        ResponseWrapper< ISO > response = restResponse.getBody();
        if ( response != null ){
            if ( !response.getBody().isEmpty() ) {
                ISO isoResponse;
                ObjectMapper mapper = new ObjectMapper();
                try {
                    byte[] object = mapper.writeValueAsBytes( response.getBody().get( 0 ) );
                    isoResponse = mapper.readValue( object, ISO.class );
                } catch ( IOException e ) {
                    LOG.error( "Error al parsear el mensaje ISO" );
                    throw new ServerException( ERROR, response.getErrorWS() );
                }
                return isoResponse;
            } else {
                throw new ServerException( ERROR, response.getErrorWS() );
            }
        }else {
            List< ErrorWS > errors = new ArrayList <>();
            errors.add( new ErrorWS( "API-02", "Invalid Response ISO" ) );
            throw new ServerException( ERROR, errors );
        }
    }
}

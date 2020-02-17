package us.gonet.r8583.rest.impl.jke;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
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
import us.gonet.serverutils.model.jke.PinBlockModel;
import us.gonet.serverutils.model.node.NodeSingleModel;
import us.gonet.serverutils.model.receipt.Receipt;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Import ( {RestServices.class, RestUtils.class} )

@Component ( "translatePinBlock" )
public class TranslatePinBlock implements IRest{

    @Autowired
    RestUtils restUtils;
    @Autowired
    RestServices services;
    private static final String PIN_BLOCK = "/sock/sendMessagePinBlock";
    private static final String ERROR = "Error al crear el Pinblock";

    @Override
    public Receipt sendReceiptRequest ( ATMRequestModel ar, String message ) throws ServerException {
        return null;
    }

    public String translatePinBlock ( String currentPinBlock, String track2, String termID, String termType, NodeSingleModel ns ) throws ServerException {
        PinBlockModel pinBlockModel = new PinBlockModel();
        pinBlockModel.setAtmLocal( "MTVK" );
        pinBlockModel.setAtmRemote( termID );
        pinBlockModel.setPinBlock( currentPinBlock );
        pinBlockModel.setTrack2( ";" + track2 + "?" );
        pinBlockModel.setTermType( termType );
        pinBlockModel.setNodeSingle( ns );
        RestTemplate rt = new RestTemplate();
        HttpEntity< PinBlockModel > requestEntity = new HttpEntity<>( pinBlockModel, restUtils.buildHttpHeaders( "jke" ) );
        ResponseEntity< ResponseWrapper > restResponse;
        try{
            restResponse = rt.exchange( services.getJkeUrl() + PIN_BLOCK, HttpMethod.POST, requestEntity, ResponseWrapper.class );
        }catch ( HttpClientErrorException | HttpServerErrorException e ){
            List< ErrorWS > errors = new ArrayList <>();
            errors.add( new ErrorWS( "API-01", "Servicio Translate PINBLOCK no disponible" ) );
            throw new ServerException( ERROR , errors );
        }
        ResponseWrapper < String > response = restResponse.getBody();
        if ( response != null ){
            if ( !response.getBody().isEmpty() ) {
                return response.getBody().get(0);
            } else {
                throw new ServerException( ERROR, response.getErrorWS() );
            }
        }else {
            List< ErrorWS > errors = new ArrayList <>();
            errors.add( new ErrorWS( "API-02", "Invalid Response JKE" ) );
            throw new ServerException( ERROR, errors );
        }
    }

    @Override
    public ISO sendMessage ( ISO isoRequest ) throws ServerException {
        return null;
    }
}

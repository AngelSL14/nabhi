package us.gonet.jxiserver.i8583.rest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import us.gonet.iso8583.constants.atm.ResponseCodes;
import us.gonet.iso8583.constants.atm.TranCodes;
import us.gonet.jxiserver.config.Properties;
import us.gonet.jxiserver.i8583.rest.IRest;
import us.gonet.jxiserver.i8583.rest.RestUtils;
import us.gonet.jxiserver.utils.UrlBuilder;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.ATMRequestModel;
import us.gonet.serverutils.model.ATMResponseModel;
import us.gonet.serverutils.model.jdb.ATD;
import us.gonet.serverutils.model.jdb.Apc;

import java.util.ArrayList;
import java.util.List;

@Component( "processingTransaction" )
public class ProcessingTransaction implements IRest {


    @Autowired @Qualifier("props")
    Properties properties;

    private static final Logger LOG = LoggerFactory.getLogger( ProcessingTransaction.class );
    private static final String ERROR_1 = "Invalid transaction";
    private static final String ERROR_2 = "Invalid URL: ";
    private static final String ERROR_3 = "Invalid Response";
    private static final String ERROR_4 = "Not Aproved";

    @Autowired
    UrlBuilder urlBuilder;

    @Autowired @Qualifier("rUtils")
    RestUtils restUtils;

    public ATMResponseModel sendTransaction(TranCodes tranCode, ATMRequestModel atmRequestModel ) throws ServerException {
        String url;
        HttpHeaders headers;
        switch ( tranCode ){
            case WITHDRAWAL:
                url = urlBuilder.buildUrl("/wdl/sendWithdrawal", "wdl");

                headers = restUtils.buildHttpHeaders( "wdl" );
                break;
            case BALANCE_INQUIRY:
                url = urlBuilder.buildUrl("/inq/sendBalanceInquiry", "inq");

                headers = restUtils.buildHttpHeaders( "inq" );
                break;
            case GENERIC_SALE:
                url = urlBuilder.buildUrl("/ges/sendGenericSale", "ges");

                headers = restUtils.buildHttpHeaders( "ges" );
                break;
            case PIN_CHANGE:
                url = urlBuilder.buildUrl("/nch/sendNipChange", "nch");

                headers = restUtils.buildHttpHeaders( "nch" );
                break;
            case STATEMENT_PRINT:
                url = urlBuilder.buildUrl("/stmt/sendStatement", "stmt");

                headers = restUtils.buildHttpHeaders( "stmt" );
                break;
            default:
                List < ErrorWS > errors = new ArrayList <>();
                errors.add( new ErrorWS( "PT-01", ERROR_1 ) );
                throw new ServerException( ERROR_1, errors );
        }
        RestTemplate rt = new RestTemplate();
        ParameterizedTypeReference<ResponseWrapper<ATMResponseModel>> rtatm = new ParameterizedTypeReference<ResponseWrapper<ATMResponseModel>>(){};
        HttpEntity < ATMRequestModel > requestEntity = new HttpEntity <>( atmRequestModel, headers );
        ResponseEntity < ResponseWrapper<ATMResponseModel> > restResponse;
        try{
            restResponse = rt.exchange( url, HttpMethod.POST, requestEntity, rtatm );
        }catch ( HttpClientErrorException | HttpServerErrorException e ){
            List< ErrorWS > errors = new ArrayList <>();
            errors.add( new ErrorWS( "PT-02", ERROR_2 +url) );
            throw new ServerException( ERROR_2, errors );
        }
        ResponseWrapper< ATMResponseModel > response = restResponse.getBody();

        if ( response != null ){
            if(LOG.isDebugEnabled())
            {
                String mes = "Respuesta de la peticion "+tranCode.name()+": "+response.getCode()+" "+ResponseCodes.getMessageFromCode(response.getCode());

                LOG.debug(mes);
            }
            if ( response.getCode().equals( "00" ) ) {
                return response.getBody().get( 0 );
            }
            else if ( response.getCode().equals( "96" ) ) {
                LOG.error( ERROR_3 );
                throw new ServerException( ERROR_3, response.getErrorWS() );
            }
            else if(response.getCode().equals( "91" ))
            {
                LOG.error( ERROR_3 );
                List<ErrorWS> errors = new ArrayList<>();
                errors.add(new ErrorWS("PT-03", ResponseCodes.getMessageFromCode(response.getCode())));
                throw new ServerException( ERROR_3, errors);
            }
            else{
                LOG.error( ERROR_4 );
                List<ErrorWS> errorWSList = new ArrayList<>();
                errorWSList.add(new ErrorWS("PT-04", ResponseCodes.getMessageFromCode(response.getCode())));
                throw new ServerException(ERROR_4, errorWSList);
            }
        }else {
            List< ErrorWS > errors = new ArrayList<>();
            errors.add( new ErrorWS( "PT-03", ERROR_3 ) );
            throw new ServerException( ERROR_3, errors );
        }
    }

    @Override
    public ATD getATD( String ip ) throws ServerException {
        return null;
    }

    @Override
    public Apc getAPC( String key ) throws ServerException {
        return null;
    }

}

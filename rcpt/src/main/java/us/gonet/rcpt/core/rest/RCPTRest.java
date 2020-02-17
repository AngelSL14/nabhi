package us.gonet.rcpt.core.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import us.gonet.rcpt.core.utils.LocalRestServices;
import us.gonet.security.utils.RestUtils;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ResponseWrapper;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.njdb.Rcpt;

import java.util.ArrayList;
import java.util.List;

@Component( "rcptRest" )
public class RCPTRest {

    @Autowired
    RestUtils restUtils;

    @Autowired
    LocalRestServices localRestServices;

    private static final String ERROR = "Servicio REST no disponible";
    private static final Logger LOG = LoggerFactory.getLogger( RCPTRest.class );

    public Rcpt getScriptReceipt( String key ) throws ServerException {
        String url = localRestServices.getJdbUrl() + "/rcptBd/" + key;
        RestTemplate rt = new RestTemplate();
        SimpleClientHttpRequestFactory rf =
                (SimpleClientHttpRequestFactory) rt.getRequestFactory();
        rf.setReadTimeout( 1000 );
        rf.setConnectTimeout( 1000 );
        ResponseEntity< ResponseWrapper< Rcpt > > re;
        try {
            ParameterizedTypeReference< ResponseWrapper< Rcpt > > rcpt = new ParameterizedTypeReference< ResponseWrapper< Rcpt > >() {};
            re = rt.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>( restUtils.buildHttpHeaders("jdb") ),
                    rcpt );
        } catch ( HttpClientErrorException | HttpServerErrorException | ResourceAccessException e ) {
            LOG.error( ERROR );
            List< ErrorWS > errors = new ArrayList<>();
            errors.add( new ErrorWS( "RCPT-04", "Invalid resource" ) );
            throw new ServerException( ERROR, errors );
        }
        ResponseWrapper< Rcpt > response = re.getBody();
        if ( response != null ){
            if ( response.getCode().equals( "00" ) ) {
                return response.getBody().get( 0 );
            }else {
                LOG.error( "Error al obtener el recibo" );
                throw new ServerException( ERROR, response.getErrorWS() );
            }
        }else {
            List< ErrorWS > errors = new ArrayList<>();
            errors.add( new ErrorWS( "RCPT-04", "Invalid resource" ) );
            throw new ServerException( ERROR, errors );
        }
    }



}

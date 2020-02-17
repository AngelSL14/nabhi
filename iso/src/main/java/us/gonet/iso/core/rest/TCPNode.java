package us.gonet.iso.core.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import us.gonet.iso.core.utils.Errors;
import us.gonet.r8583.rest.RestServices;
import us.gonet.security.utils.RestUtils;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.NodeProsa;

import java.util.ArrayList;
import java.util.List;

@Component( "tcpNode" )
public class TCPNode {

    @Autowired
    RestUtils restUtils;


    @Autowired
    RestServices restServices;

    private static final String ERROR = "Servicio REST no disponible";
    private static final Logger LOG = LoggerFactory.getLogger( TCPNode.class );

    public NodeProsa getNodeTCP ( String nodeName ) throws ServerException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl( restServices.getJdbUrl() +  "/nodeProsa/search/byNodeName" )
                .queryParam( "nodeName", nodeName );
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set( "Accept", MediaType.APPLICATION_JSON_VALUE );
        headers = restUtils.authHeader( headers, "jdb" );
        HttpEntity< ? > entity = new HttpEntity<>( headers );
        ResponseEntity< NodeProsa > response;
        try {
            response = rt.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    NodeProsa.class );
            if ( response.getStatusCodeValue() == HttpStatus.OK.value() ){
                return response.getBody();
            }else {
                List< ErrorWS > errors = new ArrayList<>();
                errors.add( new ErrorWS( Errors.INVALID_NODE_NAME.getCode(), Errors.INVALID_NODE_NAME.getValue() )  );
                throw new ServerException( ERROR, errors );
            }
        } catch ( ResourceAccessException e ) {
            LOG.error( ERROR );
            List< ErrorWS > errors = new ArrayList<>();
            errors.add( new ErrorWS( Errors.API_REST_ERROR.getCode(), Errors.API_REST_ERROR.getValue() ) );
            throw new ServerException( ERROR, errors );
        } catch (  HttpClientErrorException e ){
            List< ErrorWS > errors = new ArrayList<>();
            errors.add( new ErrorWS( Errors.INVALID_NODE_NAME.getCode(), Errors.INVALID_NODE_NAME.getValue() ) );
            throw new ServerException( ERROR, errors );
        }
    }
}

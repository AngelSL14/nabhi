package com.dksits.jke.core.rest;

import com.dksits.jke.core.util.Utilities;
import com.dksits.jke.exceptionutils.ErrorWSUtils;
import com.dksits.jke.exceptionutils.Errors;
import com.dksits.jke.model.NodeProsa;
import com.dksits.jke.model.NodeSingleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import us.gonet.r8583.rest.RestServices;
import us.gonet.security.auth.AuthIn;
import us.gonet.security.utils.RestUtils;
import us.gonet.serverutils.exceptionutils.ErrorWS;
import us.gonet.serverutils.exceptionutils.ServerException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Import( {RestServices.class} )
@Component( "tcpNode" )
public class TCPNode {

    @Autowired
    RestUtils restUtils;

    @Autowired
    AuthIn authIn;

    @Autowired
    RestServices restServices;

    @Autowired
    ErrorWSUtils errorUtils;

    private static final String ERROR = "Servicio REST no disponible";
    private static final Logger LOG = LoggerFactory.getLogger( TCPNode.class );

    public NodeProsa getNodeTCP ( String nodeName ) throws ServerException {
        RestTemplate rt = new RestTemplate();
        ParameterizedTypeReference<NodeProsa> rtatd = new ParameterizedTypeReference<NodeProsa>(){};
        RequestEntity entity;
        try {
            rt.getMessageConverters();
            entity = getNodeService(nodeName);
            ResponseEntity<NodeProsa> responseEntity = rt.exchange(entity.getUrl(), HttpMethod.GET, entity, rtatd);
            if ( responseEntity.getStatusCodeValue() == HttpStatus.OK.value() ){
                return responseEntity.getBody();
            }else {
                List< ErrorWS > errors = new ArrayList<>();
                errors.add( errorUtils.createErrorLogin( Errors.INVALID_NODE_NAME.getCode(), Errors.INVALID_NODE_NAME.getValue() ) );
                throw new ServerException( ERROR, errors );
            }
        } catch ( ResourceAccessException e ) {
            LOG.error( ERROR );
            List< ErrorWS > errors = new ArrayList<>();
            errors.add( errorUtils.createErrorLogin( Errors.API_REST_ERROR.getCode(), Errors.API_REST_ERROR.getValue() ) );
            throw new ServerException( ERROR, errors );
        } catch (  HttpClientErrorException e ){
            List< ErrorWS > errors = new ArrayList<>();
            errors.add( errorUtils.createErrorLogin( Errors.INVALID_NODE_NAME.getCode(), Errors.INVALID_NODE_NAME.getValue() ) );
            throw new ServerException( ERROR, errors );
        }
    }

    public String getZPK ( NodeSingleModel ns ) throws ServerException {
        RestTemplate rt = new RestTemplate();
        String    re;
        try {

            rt.getMessageConverters();
            ParameterizedTypeReference< String > rtatd = new ParameterizedTypeReference< String > () {};
            RequestEntity e = getZPKService(ns);
            ResponseEntity<String > responseEntity = rt.exchange(e, rtatd);
            re = responseEntity.getBody();
            if (responseEntity.getStatusCodeValue() == HttpStatus.OK.value()  ){
                return re;
            }else {
                List< ErrorWS > errors = new ArrayList<>();
                errors.add( errorUtils.createErrorLogin( Errors.INVALID_NODE_NAME.getCode(), Errors.INVALID_NODE_NAME.getValue() ) );
                throw new ServerException( Errors.INVALID_NODE_NAME.getCode(), errors );
            }
        } catch ( HttpClientErrorException | HttpServerErrorException | ResourceAccessException e ) {
            LOG.error( "Error with rest service" );
            List< ErrorWS > errors = new ArrayList<>();
            errors.add( errorUtils.createErrorLogin( "JKE-01", "Invalid resource" ) );
            throw new ServerException( ERROR, errors );
        }
        }

    private RequestEntity getZPKService(NodeSingleModel ns)  throws ServerException {
        UriTemplate uriTemplate = new UriTemplate(restServices.getJdbUrl()+"/nodeProsa/search/{nodeID}/and/{nodeName}");
        URI url = uriTemplate.expand( ns.getIdNode(), Utilities.sanitize(ns.getNodeName()));
        return  checkList(url);
    }

    private RequestEntity getNodeService(String nodeName)  throws ServerException {
        final String template = restServices.getJdbUrl()+"/nodeProsa/search/byNodeName?nodeName={nodeName}";
        LOG.debug("RestTemplate URL: " + template);
        UriTemplate uriTemplate = new UriTemplate(template);
        URI url = uriTemplate.expand(nodeName);
        HttpHeaders headers = new HttpHeaders();
        headers.set( "Accept", MediaType.APPLICATION_JSON_VALUE );
        headers = restUtils.authHeader( headers, "jdb" );

        if(Utilities.urlWhiteList(url))
        {
            return new RequestEntity(headers, HttpMethod.GET, url);

        }
        else
        {
            List<ErrorWS> errs = new ArrayList<>();
            errs.add(new ErrorWS("JKE10", "Invalid URL"));
            throw new ServerException(ERROR, errs);
        }
    }

    private RequestEntity checkList(URI url) throws ServerException {
        if(Utilities.urlWhiteList(url))
        {
            return
                    RequestEntity
                            .get(url)
                            .accept(MediaType.APPLICATION_JSON)
                            .header("Authorization",authIn.getTokenJDBServices())
                            .build()
                    ;

        }
        else
        {
            List<ErrorWS> errs = new ArrayList<>();
            errs.add(new ErrorWS("JKE10", "Invalid URL"));
            throw new ServerException(ERROR, errs);
        }
    }
    }

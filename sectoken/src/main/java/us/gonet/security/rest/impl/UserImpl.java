package us.gonet.security.rest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import us.gonet.security.auth.AuthIn;
import us.gonet.security.rest.IUser;
import us.gonet.security.rest.SecurityServices;
import us.gonet.security.rest.model.TokenUser;
import us.gonet.security.rest.model.User;

@Component ( "userImpl" )
public class UserImpl implements IUser {


    @Autowired
    SecurityServices securityServices;

    @Autowired
    AuthIn authIn;

    private static final String FIND_TOKEN = "/tokenUsers/search/byId";

    private static final String ERROR = "Servicio REST no disponible";
    private static final Logger LOG = LoggerFactory.getLogger( UserImpl.class );


    @Override
    public User findById ( String id ) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl( securityServices.getJpaUrl() + FIND_TOKEN )
                .queryParam( "id", id );
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set( "Accept", MediaType.APPLICATION_JSON_VALUE );
        HttpEntity< ? > entity = new HttpEntity<>( headers );
        ResponseEntity< TokenUser > response;
        try {
            response = rt.exchange(
                    builder.build().encode().toUri(),
                    HttpMethod.GET,
                    entity,
                    TokenUser.class );
            if ( response.getStatusCodeValue() == HttpStatus.OK.value() ){
                User user = new User();
                user.setId( id );
                user.setP( response.getBody().getClave() );
                user.setQ( ( byte ) response.getBody().getRol() );
                return user;
            }else if ( response.getStatusCodeValue() == HttpStatus.NOT_FOUND.value() ){
                return null;
            }
        } catch (  HttpClientErrorException | ResourceAccessException e ) {
            LOG.error( ERROR );
        }
        return null;
    }

}


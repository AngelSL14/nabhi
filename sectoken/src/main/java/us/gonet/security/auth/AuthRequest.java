package us.gonet.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import us.gonet.security.model.KeyAuth;
import us.gonet.security.model.ServiceAllowed;
import us.gonet.security.rest.SecurityServices;

@Component ( "authRequest" )
class AuthRequest {


    @Autowired
    SecurityServices securityServices;

    @Autowired
    Utils utils;

    private static final String AUTH = "/auth";
    private static final Logger LOG = LoggerFactory.getLogger( AuthRequest.class );


    @ExceptionHandler ( Throwable.class )
    KeyAuth authRest ( ServiceAllowed serviceAllowed ) {
        String url = securityServices.getUrls().get( serviceAllowed.getService().toLowerCase() ) + AUTH;
        if ( LOG.isDebugEnabled() ) {
            LOG.debug( String.format( "%s: %s", serviceAllowed.getService(), url ) );
        }
        return rest( url, serviceAllowed );
    }

    private KeyAuth rest ( String url, ServiceAllowed serviceAllowed ) {
        try {
            RestTemplate r = new RestTemplate();
            r.getMessageConverters();
            String s = utils.asciiToHex( serviceAllowed.getUser().toString() );
            KeyAuth k = r.postForObject( url, s, KeyAuth.class );
            return validate( k, serviceAllowed );
        } catch ( HttpServerErrorException | ResourceAccessException | HttpClientErrorException e ) {
            if ( ( e ).getLocalizedMessage().startsWith( "403" ) ) {
                LOG.error( "Bad credentials" );
            }
            LOG.error( String.format( "Service %s not available", serviceAllowed.getService() ) );
            LOG.error( String.format( "Invalid response for service %s", serviceAllowed.getService() ) );
            if ( serviceAllowed.isStop() ) {
                LOG.error( "Service required to continue" );
                serviceAllowed.setAlive( false );
            }
            return null;
        }
    }

    private KeyAuth validate ( KeyAuth k, ServiceAllowed serviceAllowed ) {
        if ( k != null ) {
            if ( k.getAccessToken() != null && ! k.getAccessToken().isEmpty() ) {
                return k;
            }
            if ( LOG.isErrorEnabled() ) {
                LOG.error( String.format( "Service: %s not available", serviceAllowed.getService() ) );
            }
            if ( serviceAllowed.isStop() ) {
                serviceAllowed.setAlive( false );
                return null;
            }
        } else {
            if ( serviceAllowed.isStop() ) {
                serviceAllowed.setAlive( false );
            }
            return null;
        }
        return null;
    }
}

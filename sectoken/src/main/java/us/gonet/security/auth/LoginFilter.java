package us.gonet.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import us.gonet.security.rest.model.User;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component( "loginFilter" )
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger LOG = LoggerFactory.getLogger( LoginFilter.class );
    private static final String URL = "/auth";
    private String key;
    @Autowired
    Utils utils;

    public void init ( String url, AuthenticationManager manager ) {
        setRequiresAuthenticationRequestMatcher( new AntPathRequestMatcher( url ) );//le pasamos la url proveniente de websecurity config
        if ( manager != null ){
            setAuthenticationManager( manager );
        }
    }

    public LoginFilter () {
        super( new AntPathRequestMatcher( URL ) );
        AuthenticationManager manager1 = authentication -> {
            if ( LOG.isErrorEnabled() ){
                LOG.error( "Invalid AuthenticationManager Manager" );
            }
            return null;
        };
        setAuthenticationManager( manager1 );
    }

    @Override
    public Authentication attemptAuthentication ( HttpServletRequest req, HttpServletResponse res ) throws IOException {
        //InputStream body = req.getInputStream();//flujo de datos// rel req.getInputSteam obtenemos el header de la peticion
        User de = utils.verifyDataFromRemoteRequest( req );
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken( de.getId(), de.getP(), Collections.emptyList() );
            AuthenticationManager authenticationManager = getAuthenticationManager();
            authentication = authenticationManager.authenticate( userToken );
        }catch ( InternalAuthenticationServiceException | BadCredentialsException ex ){
            LOG.warn( "Bad Credentials" );
        }
        return authentication;
    }

    @Override
    public void successfulAuthentication( HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth ) throws IOException {
        JwtUtil.addAuthentication( res, auth.getName(), key );
    }

    public void setKey ( String key ) {
        this.key = key;
    }
}

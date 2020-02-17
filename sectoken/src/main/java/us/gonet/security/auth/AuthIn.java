package us.gonet.security.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import us.gonet.security.model.KeyAuth;
import us.gonet.security.model.ServiceAllowed;
import us.gonet.security.rest.SecurityServices;

@Component ( "authIn" )
public class AuthIn {


    @Autowired
    AuthRequest auth;

    @Autowired
    SecurityServices services;
    private String tokenIsoServices;
    private String tokenJkeServices;
    private String tokenRCPTServices;
    private String tokenJDBServices;
    private String tokenADPServices;
    private String tokenGESServices;
    private String tokenINQServices;
    private String tokenNCHServices;
    private String tokenREVServices;
    private String tokenSRHServices;
    private String tokenSTMTServices;
    private String tokenWDLServices;


    private static final Logger LOG = LoggerFactory.getLogger( AuthIn.class );

    public void startAuthServices ( ServiceAllowed... serviceAlloweds ) {
        try {
            sutoken( serviceAlloweds );
            for ( ServiceAllowed s : serviceAlloweds ) {
                KeyAuth keyAuth = getKeyAuth( s );
                if ( keyAuth != null ) {

                    switch ( s.getService() ) {
                        case "ISO":
                            setTokenIsoServices( keyAuth.getAccessToken() );
                            break;
                        case "JKE":
                            setTokenJkeServices( keyAuth.getAccessToken() );
                            break;
                        case "RCPT":
                            setTokenRCPTServices( keyAuth.getAccessToken() );
                            break;
                        case "JDB":
                            setTokenJDBServices( keyAuth.getAccessToken() );
                            break;
                        case "ADP":
                            setTokenADPServices( keyAuth.getAccessToken() );
                            break;
                        case "GES":
                            setTokenGESServices( keyAuth.getAccessToken() );
                            break;
                        case "INQ":
                            setTokenINQServices( keyAuth.getAccessToken() );
                            break;
                        case "NCH":
                            setTokenNCHServices( keyAuth.getAccessToken() );
                            break;
                        case "REV":
                            setTokenREVServices( keyAuth.getAccessToken() );
                            break;
                        case "SRH":
                            setTokenSRHServices( keyAuth.getAccessToken() );
                            break;
                        case "STMT":
                            setTokenSTMTServices( keyAuth.getAccessToken() );
                            break;
                        case "WDL":
                            setTokenWDLServices( keyAuth.getAccessToken() );
                            break;
                        default:
                            LOG.error( "Invalid Service Name" );
                            break;
                    }
                }else {
                    if ( s.isStop() ) {
                        if ( LOG.isErrorEnabled() ){
                            LOG.error( String.format( "Invalid response for service: %s", s.getService() ) );
                        }
                        s.setAlive( false );
                    }
                }
            }
        } catch ( HttpClientErrorException e ) {
            LOG.error( "Verification failed" );
        }
    }

    private KeyAuth getKeyAuth ( ServiceAllowed s ) {
        KeyAuth keyAuth = auth.authRest( s );
        if ( keyAuth != null && LOG.isDebugEnabled() ) {
            String str = s.getService() + ": Successful key request";
            LOG.debug( str );
        }
        return keyAuth;
    }

    public String getTokenIsoServices () {
        if ( tokenIsoServices == null ){
            startAuthServices( services.getServiceAlloweds().get( "ISO" ) );
        }
        return tokenIsoServices;
    }

    private void setTokenIsoServices ( String tokenIsoServices ) {
        this.tokenIsoServices = tokenIsoServices;
    }

    public String getTokenJkeServices () {
        if ( tokenJkeServices == null ){
            startAuthServices( services.getServiceAlloweds().get( "JKE" ) );
        }
        return tokenJkeServices;
    }

    private void setTokenJkeServices ( String tokenJkeServices ) {
        this.tokenJkeServices = tokenJkeServices;
    }

    private void setTokenRCPTServices ( String tokenRCPTServices ) {
        this.tokenRCPTServices = tokenRCPTServices;
    }

    public String getTokenRCPTServices () {
        if ( tokenRCPTServices == null ){
            startAuthServices( services.getServiceAlloweds().get( "RCPT" ) );
        }
        return tokenRCPTServices;
    }

    public String getTokenJDBServices () {
        if ( tokenJDBServices == null ){
            startAuthServices( services.getServiceAlloweds().get( "JDB" ) );
        }
        return tokenJDBServices;
    }

    private void setTokenJDBServices ( String tokenJDBServices ) {
        this.tokenJDBServices = tokenJDBServices;
    }

    public String getTokenADPServices () {
        if ( tokenADPServices == null ){
            startAuthServices( services.getServiceAlloweds().get( "ADP" ) );
        }
        return tokenADPServices;
    }

    private void setTokenADPServices ( String tokenADPServices ) {
        this.tokenADPServices = tokenADPServices;
    }

    public String getTokenGESServices () {
        if ( tokenGESServices == null ) {
            startAuthServices( services.getServiceAlloweds().get( "GES" ) );
        }
        return tokenGESServices;
    }

    private void setTokenGESServices ( String tokenGESServices ) {
        this.tokenGESServices = tokenGESServices;
    }

    public String getTokenINQServices () {
        if ( tokenINQServices == null ) {
            startAuthServices( services.getServiceAlloweds().get( "INQ" ) );
        }
        return tokenINQServices;
    }

    private void setTokenINQServices ( String tokenINQServices ) {
        this.tokenINQServices = tokenINQServices;
    }

    public String getTokenNCHServices () {
        if ( tokenNCHServices == null ) {
            startAuthServices( services.getServiceAlloweds().get( "NCH" ) );
        }
        return tokenNCHServices;
    }

    private void setTokenNCHServices ( String tokenNCHServices ) {
        this.tokenNCHServices = tokenNCHServices;
    }

    public String getTokenREVServices () {
        if ( tokenREVServices == null )
            startAuthServices( services.getServiceAlloweds().get( "REV" ) );
        return tokenREVServices;
    }

    private void setTokenREVServices ( String tokenREVServices ) {
        this.tokenREVServices = tokenREVServices;
    }

    public String getTokenSRHServices () {
        if ( tokenSRHServices == null ) {
            startAuthServices( services.getServiceAlloweds().get( "SRH" ) );
        }
        return tokenSRHServices;
    }

    private void setTokenSRHServices ( String tokenSRHServices ) {
        this.tokenSRHServices = tokenSRHServices;
    }

    public String getTokenSTMTServices () {
        if ( tokenSTMTServices == null ) {
            startAuthServices( services.getServiceAlloweds().get( "STMT" ) );
        }
        return tokenSTMTServices;
    }

    private void setTokenSTMTServices ( String tokenSTMTServices ) {
        this.tokenSTMTServices = tokenSTMTServices;
    }

    public String getTokenWDLServices () {
        if ( tokenWDLServices == null ) {
            startAuthServices( services.getServiceAlloweds().get( "WDL" ) );
        }
        return tokenWDLServices;
    }

    private void setTokenWDLServices ( String tokenWDLServices ) {
        this.tokenWDLServices = tokenWDLServices;
    }

    private void sutoken( ServiceAllowed ... serviceAlloweds ){
        for ( ServiceAllowed s : serviceAlloweds ) {
            services.getServiceAlloweds().put( s.getService(), s );
        }
    }
}

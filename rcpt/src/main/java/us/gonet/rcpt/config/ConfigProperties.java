package us.gonet.rcpt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import us.gonet.rcpt.core.utils.LocalRestServices;
import us.gonet.security.auth.JwtFilter;
import us.gonet.security.auth.LoginFilter;
import us.gonet.security.rest.SecurityServices;

@Component( "configProps" )
public class ConfigProperties {

    @Autowired
    Properties props;

    @Autowired
    SecurityServices securityServices;

    @Autowired
    @Qualifier ( "localRestServices" )
    LocalRestServices localRestServices;

    @Autowired
    private LoginFilter loginFilter;

    @Autowired
    private JwtFilter jwtFilter;


    private static final Logger LOG = LoggerFactory.getLogger( ConfigProperties.class );

    public void config(){
        String jpa = props.getProtocol() + props.getJdbHost() +  ":" + props.getJdbPort() + props.getJdbPath();
        String iso = props.getProtocol() + props.getIsoHost() +  ":" + props.getIsoPort() + props.getIsoPath();
        String jke = props.getProtocol() + props.getJkeHost() +  ":" + props.getJkePort() + props.getJkePath();
        String rcpt = props.getProtocol() + props.getRcptHost() +  ":" + props.getRcptPort() + props.getRcptPath();
        securityServices.setJpaUrl ( jpa );
        securityServices.setIsoUrl ( iso );
        securityServices.setJkeUrl ( jke );
        securityServices.setRcptUrl( rcpt );
        localRestServices.setJdbUrl( jpa );
        loginFilter.setKey( props.getKey() );
        jwtFilter.setKey( props.getKey() );
        jwtFilter.setLocalUser( props.getLocalUserTkn() );
        if ( LOG.isInfoEnabled() ){
            LOG.info( "URLs configured successfully" );
        }
    }

}

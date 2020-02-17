package us.gonet.balance.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import us.gonet.r8583.rest.RestServices;
import us.gonet.security.auth.JwtFilter;
import us.gonet.security.auth.LoginFilter;
import us.gonet.security.rest.SecurityServices;

@Configuration
@Import ( {RestServices.class} )
@Component( "configProps" )
public class ConfigProperties {

    @Autowired
    Properties props;

    @Autowired
    SecurityServices securityServices;

    @Autowired
    RestServices restServices;

    @Autowired
    private JwtFilter jwtFilter;


    @Autowired
    private LoginFilter loginFilter;

    private static final Logger LOG = LoggerFactory.getLogger( ConfigProperties.class );

    public void config(){
        String jpa = props.getProtocol() + props.getJdbHost() +  ":" + props.getJdbPort() + props.getJdbPath();
        String iso = props.getProtocol() + props.getIsoHost() +  ":" + props.getIsoPort() + props.getIsoPath();
        String jke = props.getProtocol() + props.getJkeHost() +  ":" + props.getJkePort() + props.getJkePath();
        String rcpt = props.getProtocol() + props.getRcptHost() +  ":" + props.getRcptPort() + props.getRcptPath();
        String inq = props.getProtocol() + props.getInqHost() + ":" + props.getInqPort() + props.getInqPath();
        securityServices.setJpaUrl ( jpa );
        securityServices.setIsoUrl ( iso );
        securityServices.setJkeUrl ( jke );
        securityServices.setRcptUrl( rcpt );
        securityServices.setInqUrl( inq );
        restServices.setIsoUrl ( iso );
        restServices.setJkeUrl ( jke );
        restServices.setRcptUrl( rcpt );

        loginFilter.setKey(props.getKey());
        jwtFilter.setKey( props.getKey() );
        jwtFilter.setLocalUser( props.getLocalUserTkn() );
        if ( LOG.isInfoEnabled() ){
            LOG.info( "URLs configured successfully" );
        }
    }

}

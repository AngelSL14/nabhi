package us.gonet.iso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import us.gonet.iso.security.LoopExecute;
import us.gonet.r8583.rest.RestServices;
import us.gonet.security.auth.JwtFilter;
import us.gonet.security.auth.LoginFilter;
import us.gonet.security.rest.SecurityServices;

@Configuration
@Import( {RestServices.class} )
@Component ( "configProps" )
public class ConfigProperties{

    @Autowired
    LoopExecute loopExecute;

    @Autowired
    Properties props;

    @Autowired
    SecurityServices securityServices;

    @Autowired
    RestServices restServices;

    @Autowired
    private LoginFilter loginFilter;

    @Autowired
    private JwtFilter jwtFilter;


    public void config(){
        String jpa = props.getProtocol() + props.getJdbHost() +  ":" + props.getJdbPort() + props.getJdbPath();
        securityServices.setJpaUrl ( jpa );
        restServices.setJdbUrl( jpa );
        loopExecute.refreshToken();
        loginFilter.setKey( props.getKey() );
        jwtFilter.setKey( props.getKey() );
        jwtFilter.setLocalUser( props.getLocalUserTkn() );
    }



}

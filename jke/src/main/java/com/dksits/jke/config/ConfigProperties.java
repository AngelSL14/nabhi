package com.dksits.jke.config;

import com.dksits.jke.security.LoopExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.r8583.rest.RestServices;
import us.gonet.security.auth.JwtFilter;
import us.gonet.security.auth.LoginFilter;
import us.gonet.security.rest.SecurityServices;

@Component ( "configProps" )
public class ConfigProperties {

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


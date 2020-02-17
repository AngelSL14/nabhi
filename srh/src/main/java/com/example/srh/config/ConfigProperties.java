package com.example.srh.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import us.gonet.security.auth.JwtFilter;
import us.gonet.security.auth.LoginFilter;
import us.gonet.security.rest.SecurityServices;

@Configuration
@Component("configProps")
public class ConfigProperties {

    @Autowired
    @Qualifier("props")
    Properties props;

    @Autowired
    SecurityServices securityServices;

    @Autowired
    private LoginFilter loginFilter;

    @Autowired
    private JwtFilter jwtFilter;

    private static final Logger LOG = LoggerFactory.getLogger( ConfigProperties.class );

    public void config(){
        String jpa = props.getProtocol() + props.getJdbHost() +  ":" + props.getJdbPort() + props.getJdbPath();
        securityServices.setJpaUrl( jpa );

        loginFilter.setKey( props.getKey() );
        jwtFilter.setKey( props.getKey() );
        jwtFilter.setLocalUser( props.getLocalUserTkn() );

        if(LOG.isInfoEnabled()){
            LOG.info("URLs configured successfully");
        }
    }


}

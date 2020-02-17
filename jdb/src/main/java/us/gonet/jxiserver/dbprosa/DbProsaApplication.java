package us.gonet.jxiserver.dbprosa;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import us.gonet.jxiserver.dbprosa.config.JdbProperties;
import us.gonet.security.auth.JwtFilter;
import us.gonet.security.auth.LoginFilter;
import us.gonet.security.rest.SecurityServices;

import javax.swing.*;


@SpringBootApplication
public class DbProsaApplication extends SpringBootServletInitializer implements CommandLineRunner{
    @Autowired
    JdbProperties jdbProperties;

    @Autowired
    SecurityServices securityServices;


    @Autowired
    private LoginFilter loginFilter;

    @Autowired
    private JwtFilter jwtFilter;

    private static final Logger LOG = LoggerFactory.getLogger( DbProsaApplication.class );

    @Bean
    public ModelMapper mapper()
    {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(DbProsaApplication.class, args);
    }

    @Override
    public void run(String... strings){
        securityServices.setJpaUrl(jdbProperties.getProtocol()+ jdbProperties.getHost() + ":"
                + jdbProperties.getPort() + jdbProperties.getPath());
        loginFilter.setKey( jdbProperties.getKey() );
        jwtFilter.setKey( jdbProperties.getKey() );
        jwtFilter.setLocalUser( jdbProperties.getLocalUserTkn() );

        if(LOG.isInfoEnabled())
        {
            LOG.info("URLs configured successfully");
        }
    }
}

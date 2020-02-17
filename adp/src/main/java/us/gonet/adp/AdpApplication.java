package us.gonet.adp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import us.gonet.adp.config.Properties;
import us.gonet.adp.security.LoopExecute;
import us.gonet.security.auth.JwtFilter;
import us.gonet.security.auth.LoginFilter;
import us.gonet.security.rest.SecurityServices;

@SpringBootApplication
public class AdpApplication extends SpringBootServletInitializer implements CommandLineRunner {
	@Autowired
	Properties properties;

	@Autowired
	SecurityServices securityServices;

	@Autowired
    LoopExecute loopExecute;

    @Autowired
    private LoginFilter loginFilter;

    @Autowired
    private JwtFilter jwtFilter;


    public static void main( String [] atr ){
		SpringApplication.run( AdpApplication.class );
	}
	@Override
	public void run(String... strings) throws Exception {
		securityServices.setJpaUrl(properties.getProtocol()+ properties.getHost() + ":" + properties.getPort() + properties.getPath());

		loginFilter.setKey( properties.getKey() );
		jwtFilter.setKey( properties.getKey() );
		jwtFilter.setLocalUser( properties.getLocalUserTkn() );

		loopExecute.refreshToken();
	}
}

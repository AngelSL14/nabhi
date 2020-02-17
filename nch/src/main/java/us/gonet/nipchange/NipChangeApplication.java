package us.gonet.nipchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import us.gonet.nipchange.config.ConfigProperties;
import us.gonet.nipchange.security.LoopExecute;

@SpringBootApplication
public class NipChangeApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    ConfigProperties configProps;

    @Autowired
    LoopExecute loopExecute;

    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application) {
        return application.sources(NipChangeApplication.class);
    }

    @Override
    public void run( String... args ){
        configProps.config();
        loopExecute.refreshToken();
    }

}

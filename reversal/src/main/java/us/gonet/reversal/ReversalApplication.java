package us.gonet.reversal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import us.gonet.reversal.config.ConfigProperties;
import us.gonet.reversal.security.LoopExecute;


@SpringBootApplication
public class ReversalApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    ConfigProperties configProps;

    @Autowired
    LoopExecute loopExecute;

    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application) {
        return application.sources( ReversalApplication.class);
    }

    @Override
    public void run( String... args ){
        configProps.config();
        loopExecute.refreshToken();
    }
}

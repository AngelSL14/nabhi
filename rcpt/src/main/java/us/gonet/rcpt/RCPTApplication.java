package us.gonet.rcpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import us.gonet.rcpt.config.ConfigProperties;
import us.gonet.rcpt.security.LoopExecute;

@SpringBootApplication
public class RCPTApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    ConfigProperties configProps;

    @Autowired
    LoopExecute loopExecute;
    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application) {
        return application.sources(RCPTApplication.class);
    }

    @Override
    public void run ( String... args ) throws Exception {
        configProps.config();
        loopExecute.refreshToken();
    }
}

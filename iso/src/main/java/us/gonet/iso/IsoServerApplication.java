package us.gonet.iso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import us.gonet.iso.config.ConfigProperties;
import us.gonet.iso.security.LoopExecute;

@SpringBootApplication
public class IsoServerApplication extends SpringBootServletInitializer implements CommandLineRunner {


    @Autowired
    ConfigProperties configProps;

    @Autowired
    LoopExecute loopExecute;

    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application) {
        return application.sources(IsoServerApplication.class);
    }

    @Override
    public void run( String... args ){
        configProps.config();
        loopExecute.refreshToken();
    }
}

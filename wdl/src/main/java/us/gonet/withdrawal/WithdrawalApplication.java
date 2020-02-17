package us.gonet.withdrawal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import us.gonet.withdrawal.config.ConfigProperties;
import us.gonet.withdrawal.security.LoopExecute;


@SpringBootApplication
public class WithdrawalApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    ConfigProperties configProps;

    @Autowired
    LoopExecute loopExecute;


    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application) {
        return application.sources(WithdrawalApplication.class);
    }

    @Override
    public void run( String... args ){
        configProps.config();
        loopExecute.refreshToken();
    }
}

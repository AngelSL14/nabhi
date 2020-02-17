package us.gonet.jxiserver;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import us.gonet.jxiserver.config.ConfigProperties;
import us.gonet.jxiserver.security.auth.LoopExecute;

@SpringBootApplication
public class JxiserverApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    LoopExecute loopExecute;

    @Autowired
    ConfigProperties conf;


    @Override
    public void run(String... strings) throws Exception
    {
        conf.config();
        loopExecute.refrestToken();
    }

    @Bean
    public Gson gson()
    {
        return new Gson();
    }

}

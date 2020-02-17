package com.example.srh;

import com.example.srh.config.ConfigProperties;
import com.example.srh.security.LoopExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SrhApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Autowired
    @Qualifier("configProps")
    ConfigProperties props;

    @Autowired
    @Qualifier("loopExecute")
    LoopExecute execute;

    @Override
    public void run(String... args) throws Exception {
        props.config();
        execute.refreshToken();
    }
}

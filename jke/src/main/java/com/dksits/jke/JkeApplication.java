package com.dksits.jke;

import com.dksits.jke.config.ConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class JkeApplication extends SpringBootServletInitializer implements CommandLineRunner {

	@Autowired
	ConfigProperties configProps;

	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder application) {
		return application.sources(JkeApplication.class);
	}

	@Override
	public void run( String... args ){
		configProps.config();
	}

}

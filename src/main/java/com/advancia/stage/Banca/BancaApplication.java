package com.advancia.stage.Banca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BancaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BancaApplication.class, args);
	}
	
	// METODO PER IL BUILD
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BancaApplication.class);
    }

}
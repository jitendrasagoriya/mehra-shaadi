package com.jitendra.mehra.shaadi;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication 
public class MehraShaadiApplication implements ApplicationRunner {

	private static final Logger logger =   LogManager.getLogger(MehraShaadiApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(MehraShaadiApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("Mehra Shaadi Application. Application Started....");		
	}

	
	
}

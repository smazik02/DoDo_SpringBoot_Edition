package com.stanley.dodospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DoDoSpringBootEditionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoDoSpringBootEditionApplication.class, args);
	}

}

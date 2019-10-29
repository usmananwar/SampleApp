package com.usman;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.usman.aws.CognitoService;

@SpringBootApplication
public class SampleApp {

	public static void main(String[] args) {
		// SpringApplication.run(SampleApp.class, args);

		CognitoService service = new CognitoService();

		service.authenticate("usman.a", "usman123");

	}

}

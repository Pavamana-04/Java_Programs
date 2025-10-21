package com.spring.boot.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SimpleprojectApplication {

	public static void main(String[] args) {
		

		ApplicationContext context=SpringApplication.run(SimpleprojectApplication.class, args);

		Coders coders=context.getBean(Coders.class);
		coders.code();
		coders.getBugs().fixBugs();
		coders.getError().showError();
	}

}

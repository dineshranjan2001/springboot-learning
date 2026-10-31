package com.springboot.pratice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class PraticeApplication {

	public static void main(String[] args) {

		SpringApplication.run(PraticeApplication.class, args);
		System.out.println("Hello Spring boot");
	}

}

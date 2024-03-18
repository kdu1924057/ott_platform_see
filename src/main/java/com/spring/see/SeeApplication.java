package com.spring.see;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeeApplication.class, args);
	}
}


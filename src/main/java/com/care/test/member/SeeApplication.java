package com.care.test.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.care.test.admin"})
public class SeeApplication {
	public static void main(String[] args) throws SQLException {

		SpringApplication.run(SeeApplication.class, args);

	}
}

package com.care.test.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.sql.SQLException;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AdminApplication {
    public static void main(String[] args) throws SQLException {

        //SpringApplication.run(AdminApplication.class, args);

    }
}

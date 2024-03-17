package com.care.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@SpringBootApplication
public class SeeApplication {
	public static void main(String[] args) throws SQLException {

		SpringApplication.run(SeeApplication.class, args);

//		try{
//			Class.forName("org.postgresql.Driver");
//		}catch(ClassNotFoundException e){
//			System.out.println("where is your Postgresql jdbc driver?");
//			e.printStackTrace();
//		}
//		System.out.println("postgresql jdbc driver registered");
//
//		Connection connection=null;
//
//		try{
//			connection = DriverManager.getConnection(
//					"jdbc:postgresql://pjsee.c5qektyoousk.ap-northeast-2.rds.amazonaws.com:5432/see_member", "postgres", "tmddus1006");
//		}catch(SQLException e){
//			System.out.println("Connection Failed");
//			e.printStackTrace();
//		}
//
//		if(connection!=null){
//			System.out.println(connection);
//			System.out.println("Connected");
//		}else{
//			System.out.println("Failed Connected");
//		}
//
//		connection.close();
//	}

	}
}

package com.example.MyTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.example.MyTest.repository")
public class MyTest2Application {

	public static void main(String[] args) {
		SpringApplication.run(MyTest2Application.class, args);
	}
}

package com.example.moduleclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ClientApplcation {
	public static void main(String[] args) {
		SpringApplication.run(ClientApplcation.class, args);
	}
}

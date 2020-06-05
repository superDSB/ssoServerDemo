package com.lysstudy.ssodemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SsodemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsodemoApplication.class, args);
	}
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}

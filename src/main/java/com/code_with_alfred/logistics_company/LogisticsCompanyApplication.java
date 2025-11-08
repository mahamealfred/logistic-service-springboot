package com.code_with_alfred.logistics_company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LogisticsCompanyApplication {
	public static void main(String[] args) {
		SpringApplication.run(LogisticsCompanyApplication.class, args);
	}
}
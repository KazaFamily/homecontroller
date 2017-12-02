package com.kazafamily.homecontroller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.employmeo.data", "com.talytica.portal", "com.talytica.common"})
public class HomeControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeControlApplication.class, args);
	}
}

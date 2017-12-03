package com.kazafamily.homecontroller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.kazafamily.homecontroller"})
public class HomeControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeControlApplication.class, args);
	}
}

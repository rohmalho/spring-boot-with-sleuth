package com.example.eShopping.pricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.eShopping.logger","com.example.eShopping.pricing"})
public class PricingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PricingApplication.class, args);
	}

}

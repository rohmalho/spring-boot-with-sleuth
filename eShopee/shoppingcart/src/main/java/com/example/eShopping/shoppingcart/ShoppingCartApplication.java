package com.example.eShopping.shoppingcart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.eShopping.logger","com.example.eShopping.shoppingcart"})
@EnableFeignClients
public class ShoppingCartApplication {

	public static void main(String[] args) {

		SpringApplication.run(ShoppingCartApplication.class, args);
	}

}

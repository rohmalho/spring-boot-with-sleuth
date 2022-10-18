package com.example.eShopping.logger;

import com.example.eShopping.logger.logging.LogFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableAutoConfiguration
public class LoggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoggerApplication.class, args);
	}

	@Bean
	public LogFilter auditFilter(){
		return  new LogFilter();
	}

}

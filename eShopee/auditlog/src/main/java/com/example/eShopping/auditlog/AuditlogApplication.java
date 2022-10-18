package com.example.eShopping.auditlog;

import com.example.eShopping.auditlog.logging.AuditFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuditlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditlogApplication.class, args);
	}

	@Bean
	public AuditFilter auditFilter(){
		return  new AuditFilter();
	}

}

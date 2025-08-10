package com.ahmetmert.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.ahmetmert"})
@EnableJpaRepositories(basePackages = {"com.ahmetmert"})
@ComponentScan(basePackages = {"com.ahmetmert"})
@SpringBootApplication
public class HotelAutomationProjectApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(HotelAutomationProjectApplicationStarter.class, args);
	}

}

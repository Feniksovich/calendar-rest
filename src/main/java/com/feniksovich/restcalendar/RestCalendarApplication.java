package com.feniksovich.restcalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RestCalendarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestCalendarApplication.class, args);
	}

}

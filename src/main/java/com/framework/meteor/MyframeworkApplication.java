package com.framework.meteor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MyframeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyframeworkApplication.class, args);
	}

}

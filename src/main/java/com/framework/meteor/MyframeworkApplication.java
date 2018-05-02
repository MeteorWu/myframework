package com.framework.meteor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class MyframeworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyframeworkApplication.class, args);
	}

}

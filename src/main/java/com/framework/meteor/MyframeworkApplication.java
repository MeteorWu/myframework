package com.framework.meteor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@MapperScan

@EnableTransactionManagement
@SpringBootApplication
@EnableJpaAuditing
public class MyframeworkApplication {

//	@Autowired
//	private DBConfig dbConfig;
//
//	@Bean
//	public DataSource dataSource(){
//		return dbConfig.dataSource();
//	}

	public static void main(String[] args) {
		SpringApplication.run(MyframeworkApplication.class, args);
	}

}

package com.framework.meteor.framework.datasource;

import com.framework.meteor.framework.util.Base64Encoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * 数据库用户密码配置
 *
 * @author Meteor.wu
 * @since 2017/11/29 14:09
 */
//@Configuration
//@ComponentScan
//@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
public class DBConfig {
    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @Value("${spring.datasource.url}")
    private String url;

//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return  new DriverManagerDataSource(url, username, Base64Encoder.aTOm(password));
    }
}

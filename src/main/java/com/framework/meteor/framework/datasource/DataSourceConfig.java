package com.framework.meteor.framework.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据库配置
 *
 * @author Meteor.wu
 * @since 2017/12/5 10:28
 */
@Configuration
public class DataSourceConfig {
    @Bean(name = "db1")
    @Qualifier("db1")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.db1") // application.properteis中对应属性的前缀
    public DataSource db1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2")
    @Qualifier("db2")
    @ConfigurationProperties(prefix="spring.datasource.db2")
    public DataSource db2() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db3")
    @Qualifier("db3")
    @ConfigurationProperties(prefix="spring.datasource.db3")
    public DataSource db3() {
        return DataSourceBuilder.create().build();
    }
}

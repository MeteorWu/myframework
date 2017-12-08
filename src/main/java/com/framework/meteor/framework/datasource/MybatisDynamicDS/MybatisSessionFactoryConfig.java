package com.framework.meteor.framework.datasource.MybatisDynamicDS;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis session factory config
 *
 * @author Meteor.wu
 * @since 2017/12/8 11:21
 */
@Configuration
@MapperScan(basePackages = {"com.framework.meteor.work"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisSessionFactoryConfig {

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Autowired
    private DbSelectorInterceptor dbSelectorInterceptor;

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource); // 使用titan数据源, 连接titan库
        factoryBean.setPlugins(new Interceptor[]{dbSelectorInterceptor});
        return factoryBean.getObject();

    }
}

package com.framework.meteor.framework.datasource.JpaMultiDS;

/**
 * jpa数据源配置
 *
 * @author Meteor.wu
 * @since 2017/12/5 11:44
 */

//import org.springframework.context.annotation.Primary;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef="entityManagerFactoryDb1",
//        transactionManagerRef="transactionManagerDb1",
//        basePackages= { "com.framework.meteor.work.user.model" }) //设置Repository所在位置
public class JpaDbAConfig {
//    //注入db1数据源
//    @Autowired
//    @Qualifier("db1")
//    private DataSource db1;
//
//    //配置EntityManager实体
//    @Primary
//    @Bean(name = "entityManagerDb1")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactoryDb1(builder).getObject().createEntityManager();
//    }
//
//    //配置EntityManager工厂实体
//    @Primary
//    @Bean(name = "entityManagerFactoryDb1")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDb1 (EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(db1)
//                .properties(getVendorProperties(db1))
//                .packages("com.framework.meteor.work.user.model") //设置应用creditDataSource的基础包名
//                .persistenceUnit("db1PersistenceUnit")
//                .build();
//    }
//
//    //注入jpa配置实体
//    @Autowired
//    private JpaProperties jpaProperties;
//
//    //获取jpa配置信息
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
//    }
//
//    //配置事务
//    @Primary
//    @Bean(name = "transactionManagerDb1")
//    public PlatformTransactionManager transactionManagerDb1(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryDb1(builder).getObject());
//    }
}

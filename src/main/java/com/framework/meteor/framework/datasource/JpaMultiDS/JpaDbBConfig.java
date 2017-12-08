package com.framework.meteor.framework.datasource.JpaMultiDS;

/**
 * jpa数据源配置
 * @author Meteor.wu
 * @since 2017/12/5 14:37
 */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef="entityManagerFactoryDb2",
//        transactionManagerRef="transactionManagerDb2",
//        basePackages= { "com.framework.meteor.work.client.model" }) //设置Repository所在位置
public class JpaDbBConfig {
//    //注入db2数据源
//    @Autowired
//    @Qualifier("db2")
//    private DataSource db2;
//
//    //配置EntityManager实体
//    @Bean(name = "entityManagerDb2")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactoryDb2(builder).getObject().createEntityManager();
//    }
//
//    //配置EntityManager工厂实体
//    @Bean(name = "entityManagerFactoryDb2")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDb2 (EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(db2)
//                .properties(getVendorProperties(db2))
//                .packages("com.framework.meteor.work.client.model") //设置应用creditDataSource的基础包名
//                .persistenceUnit("db2PersistenceUnit")
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
//    @Bean(name = "transactionManagerDb2")
//    public PlatformTransactionManager transactionManagerDb2(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryDb2(builder).getObject());
//    }
}

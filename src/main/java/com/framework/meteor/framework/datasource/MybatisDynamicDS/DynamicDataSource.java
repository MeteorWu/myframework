package com.framework.meteor.framework.datasource.MybatisDynamicDS;

import com.framework.meteor.framework.constant.DynamicDSEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 配置动态数据源
 *
 * @author Meteor.wu
 * @since 2017/12/7 15:16
 */
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    private Object writeDataSource; //写数据源
    private List<Object> readDataSources; //多个读数据源
    protected int readDataSourceSize; //读数据源个数
    private int readDataSourcePollPattern = 0; //获取读数据源方式，0：随机，1：轮询
    private AtomicLong counter = new AtomicLong(0);
    private static final Long MAX_POOL = Long.MAX_VALUE;
    private final Lock lock = new ReentrantLock();


    //注入db1数据源
    @Autowired
    @Qualifier("db1")
    private DataSource db1;

    //注入db2数据源
    @Autowired
    @Qualifier("db2")
    private DataSource db2;

    //注入db3数据源
    @Autowired
    @Qualifier("db3")
    private DataSource db3;

    @Autowired
    private OPCountMapper opCountMapper;

//    @Bean
//    public DynamicDataSource dynamicDataSource() {
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DynamicDSEnum.WRITE_ONLY.name()+0, db1);
//
//        List list = new ArrayList();
//        list.add(db2);
//        targetDataSources.put(DynamicDSEnum.READ_ONLY.name() + 0, db2);
//        targetDataSources.put(DynamicDSEnum.READ_ONLY.name() + 1, db3);
//        readDataSourceSize = 2;
//        this.setTargetDataSources(targetDataSources);
//
//        super.setDefaultTargetDataSource(db1);
//        return this;
//    }


    @Override
    public void afterPropertiesSet() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDSEnum.WRITE_ONLY.name()+0, db1);

        List list = new ArrayList();
        list.add(db2);
        targetDataSources.put(DynamicDSEnum.READ_ONLY.name() + 0, db2);
        targetDataSources.put(DynamicDSEnum.READ_ONLY.name() + 1, db3);
        readDataSourceSize = 2;
        super.setTargetDataSources(targetDataSources);

        super.setDefaultTargetDataSource(db1);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String currentKey = DataSourceSelector.getCurrentKey();
        return opCountMapper.getDataSourceString(currentKey, readDataSourceSize);
    }
}

package com.framework.meteor.framework.datasource.MybatisDynamicDS;

/**
 * 动态数据源拦截器
 * 对update使用写库，对query使用读库
 *
 * @author Meteor.wu
 * @since 2017/12/7 16:06
 */

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Intercepts({
        @Signature(
                type= Executor.class,
                method = "update",
                args = {MappedStatement.class,Object.class}),
        @Signature(
                type= Executor.class,
                method = "query",
                args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class,CacheKey.class,BoundSql.class}),
        @Signature(
                type= Executor.class,
                method = "query",
                args = {MappedStatement.class,Object.class,RowBounds.class, ResultHandler.class}),})    //http://www.jianshu.com/p/d93cd919f5ee
@Component
public class DbSelectorInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String name = invocation.getMethod().getName();
        if(name.equals("update"))
            DataSourceSelector.setWO();
        if(name.equals("query"))
            DataSourceSelector.setRO();
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if(target instanceof Executor)
            return Plugin.wrap(target,this);
        else return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}

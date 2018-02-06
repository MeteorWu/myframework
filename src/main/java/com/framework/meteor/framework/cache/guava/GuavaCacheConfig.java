package com.framework.meteor.framework.cache.guava;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * guava cache config
 * @author Meteor.wu
 * @since 2018/2/6 14:45
 */
@Configuration
@EnableCaching
public class GuavaCacheConfig {
    private GuavaCache cache(){
        return new GuavaCache("meteor",
                CacheBuilder.newBuilder()
                        .recordStats()
                        .maximumSize(100000)
                        .expireAfterWrite(30, TimeUnit.MINUTES)
                        .build());
    }

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager manager = new SimpleCacheManager();
        List list = new ArrayList();
        list.add(cache());
        manager.setCaches(  list );
        return manager;
    }
}

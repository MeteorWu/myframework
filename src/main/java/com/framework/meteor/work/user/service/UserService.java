package com.framework.meteor.work.user.service;

import com.framework.meteor.work.user.dao.UserJpaDao;
import com.framework.meteor.work.user.dao.UserMyBatisDao;
import com.framework.meteor.work.user.model.User;
import com.framework.meteor.work.user.model.UserTestRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Meteor.wu
 * @since 2017/12/12 18:24
 */
@Service
public class UserService {

    @Autowired
    private UserJpaDao userJpaDao;

    @Autowired
    private UserMyBatisDao userMyBatisDao;

    @Caching(put = {
        @CachePut(value = "redis", key = "'user'+#user.getUserId()"), // 如果这里有key设置，那么redisConfig里面的KeyGenerator会忽略
        @CachePut(value = "redis", key = "'user'+#user.phone()")
    })
    public User save(User user) {
        User user1 = userJpaDao.save(user);
        return user1;
    }

    @Caching(put = {// 可以同时缓存2个
            @CachePut(value = "redis", key = "'user'+#user.getUserId()"),
            @CachePut(value = "redis", key = "'user'+#user.phone()")
    })
    public User update(User user){
        return userJpaDao.save(user);
    }

    @Cacheable(value = "redis", key = "'user'+#id")
    public User getById(String id) {
        return userJpaDao.findOne(id);
    }

    @Cacheable(value = "redis", key = "'user'+#phone")
    public User getByPhone(String phone) {
        return userJpaDao.getByPhone(phone);
    }


    public List<User> getAll() {
        return userJpaDao.findAll();
    }

    @CacheEvict(value = "redis", key = "'user'+#id")
    public void delete(String id) {
        userJpaDao.delete(id);
    }

    @Cacheable(value = "redis", key = "'other'+#result.otherId", condition = "#result != null")
    public UserTestRecordDTO getOtherByUser(String userId) {
        return userMyBatisDao.getOtherByUser(userId);
    }
}

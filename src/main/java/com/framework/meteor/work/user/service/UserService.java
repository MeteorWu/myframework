package com.framework.meteor.work.user.service;

import com.framework.meteor.work.user.dao.UserJpaDao;
import com.framework.meteor.work.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

    @CachePut(value = "redis", key = "#user.getUserId()")// 如果这里有key设置，那么redisConfig里面的KeyGenerator会忽略
    public User save(User user) {
        User user1 = userJpaDao.save(user);
        return user1;
    }

    @CachePut(value = "redis", key = "#user.getUserId()")
    public User update(User user){
        return userJpaDao.save(user);
    }

    @Cacheable(value = "redis", key = "#id")
    public User getById(String id) {
        return userJpaDao.findOne(id);
    }

    public List<User> getAll() {
        return userJpaDao.findAll();
    }

    @CacheEvict(value = "redis", key = "#id")
    public void delete(String id) {
        userJpaDao.delete(id);
    }
}

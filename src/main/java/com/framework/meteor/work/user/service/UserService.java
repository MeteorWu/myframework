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

    @CachePut(value = "users", key = "#user.getUserId()")
    public void save(User user) {
        User user1 = userJpaDao.save(user);
    }

    @Cacheable(value = "users")
    public User getById(String id) {
        return userJpaDao.getOne(id);
    }

    @Cacheable(value = "users")
    public List<User> getAll() {
        return userJpaDao.findAll();
    }

    @CacheEvict(value = "users", key = "#id", condition = "#id")
    public void delete(String id) {
        userJpaDao.delete(id);
    }
}

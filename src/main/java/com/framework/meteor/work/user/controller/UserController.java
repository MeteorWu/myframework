package com.framework.meteor.work.user.controller;

import com.framework.meteor.framework.constant.ResultMsg;
import com.framework.meteor.framework.model.Response;
import com.framework.meteor.framework.model.ResponseBody;
import com.framework.meteor.framework.util.UUIDUtil;
import com.framework.meteor.work.user.dao.UserJpaDao;
import com.framework.meteor.work.user.dao.UserMyBatisDao;
import com.framework.meteor.work.user.model.User;
import com.framework.meteor.work.user.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户模块管理
 *
 * @author Meteor.wu
 * @since 2017/11/29 15:24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMyBatisDao userMyBatisDao;

    @Autowired
    private UserJpaDao userJpaDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @PostMapping("/getById")
    public Response getById(@RequestBody JSONObject jsonObject) {
        return new ResponseBody<>(ResultMsg.SUCCESS, userMyBatisDao.getById("a690f83a6eb648b4a7a32e45a41c1197"));
    }

    @PostMapping("/getUserList")
    public Response getUserList() {
        return new ResponseBody<>(ResultMsg.SUCCESS, userMyBatisDao.getUserList());
    }

    @PostMapping("/addUser")
    public Response addUser(@RequestBody JSONObject jsonObject) {
        User user = new User();
        user.setUserId(UUIDUtil.getUUID());
        user.setUsername("zhagnsan");
        user.setSex(1);
        userService.save(user);

        return new ResponseBody<>(ResultMsg.SUCCESS);
    }

    @PostMapping("/getAll")
    public Response getAll(@RequestBody JSONObject jsonObject) {

        return new ResponseBody<>(ResultMsg.SUCCESS, userService.getAll());
    }

    @PostMapping("/getById1")
    public Response getById1(@RequestBody JSONObject jsonObject) {
        User user = userService.getById(jsonObject.getString("id"));
        List<User> users = new ArrayList<>();
        users.add(user);
        return new ResponseBody<>(ResultMsg.SUCCESS, users);
    }

    @PostMapping("/delete")
    public Response delete(@RequestBody JSONObject jsonObject) {
        userService.delete(jsonObject.getString("id"));
        return new ResponseBody<>(ResultMsg.SUCCESS);
    }

    @PostMapping("/show")
    public Response show(@RequestBody JSONObject jsonObject) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        SetOperations setOperations = redisTemplate.opsForSet();

        return new ResponseBody<>(ResultMsg.SUCCESS);
    }
}

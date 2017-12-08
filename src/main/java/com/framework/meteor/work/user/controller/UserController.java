package com.framework.meteor.work.user.controller;

import com.framework.meteor.framework.constant.ResultMsg;
import com.framework.meteor.framework.model.Response;
import com.framework.meteor.framework.model.ResponseBody;
import com.framework.meteor.work.user.dao.UserMyBatisDao;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/getById")
    public Response getById(@RequestBody JSONObject jsonObject) {
        return new ResponseBody<>(ResultMsg.SUCCESS, userMyBatisDao.getById("a690f83a6eb648b4a7a32e45a41c1197"));
    }

    @PostMapping("/getUserList")
    public Response getUserList(){
        return new ResponseBody<>(ResultMsg.SUCCESS, userMyBatisDao.getUserList());
    }
}

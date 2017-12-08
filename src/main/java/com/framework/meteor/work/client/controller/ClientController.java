package com.framework.meteor.work.client.controller;

import com.framework.meteor.framework.constant.ResultMsg;
import com.framework.meteor.framework.model.Response;
import com.framework.meteor.framework.model.ResponseBody;
import com.framework.meteor.work.client.dao.ClientMyBatisDao;
import com.framework.meteor.work.client.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户模块controller
 *
 * @author Meteor.wu
 * @since 2017/12/4 16:58
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientMyBatisDao clientMyBatisDao;

    @PostMapping("/getById")
    public Response getById() {
        Client client = new Client();
        client.setClientName("client1");
        client.setPhone("13165716108");
        clientMyBatisDao.insert(client);
        return new ResponseBody<>(ResultMsg.SUCCESS, client.getClientId());
    }
}

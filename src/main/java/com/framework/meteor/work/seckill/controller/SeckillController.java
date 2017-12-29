package com.framework.meteor.work.seckill.controller;

import com.framework.meteor.framework.constant.ResultMsg;
import com.framework.meteor.framework.model.Response;
import com.framework.meteor.framework.model.ResponseBody;
import com.framework.meteor.work.seckill.model.Seckill;
import com.framework.meteor.work.seckill.serivce.SeckillServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒杀controller
 *
 * @author Meteor.wu
 * @since 2017/12/18 16:08
 */
@RestController
@RequestMapping("/seckill")
public class SeckillController  {

    @Autowired
    private SeckillServiceImpl seckillService;

    @PostMapping("/seckill")
    public Response seckilll(@RequestBody Seckill seckilll) {
        seckillService.seckill(seckilll);
        return new ResponseBody<>(ResultMsg.SUCCESS);
    }
}

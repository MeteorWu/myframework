package com.framework.meteor.work.seckill.serivce;

import com.framework.meteor.framework.mq.Sender;
import com.framework.meteor.work.seckill.model.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 秒杀业务逻辑实现
 *
 * @author Meteor.wu
 * @since 2017/12/18 16:10
 */
@Service
public class SeckillServiceImpl  {

    @Autowired
    private Sender sender;


    public void getSeckilllInfo(String id) {

    }

    public void seckill(Seckill seckill) {
        sender.send(seckill);
    }
}

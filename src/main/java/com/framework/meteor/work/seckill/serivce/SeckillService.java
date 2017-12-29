package com.framework.meteor.work.seckill.serivce;

import com.framework.meteor.work.seckill.model.Seckill;

/**
 * 秒杀业务逻辑
 *
 * @author Meteor.wu
 * @since 2017/12/18 16:09
 */

public interface SeckillService {
    void getSeckilllInfo(String id);

    void seckill(Seckill seckill);
}

package com.framework.meteor.work.seckill.serivce;

import com.framework.meteor.work.seckill.dao.ActivityDao;
import com.framework.meteor.work.seckill.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 活动业务
 *
 * @author Meteor.wu
 * @since 2017/12/29 14:18
 */
@Service
public class ActivityService {

    @Autowired
    private ActivityDao activityDao;

    public void seckillPrize(String activityId) {
        activityDao.seckillPrize(activityId);
    }

    public Activity getById(String activityId) {
        return activityDao.getOne(activityId);
    }
}

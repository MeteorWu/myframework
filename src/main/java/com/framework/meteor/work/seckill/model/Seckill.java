package com.framework.meteor.work.seckill.model;

import javax.persistence.Column;
import java.io.Serializable;

/**
 * 秒杀
 *
 * @author Meteor.wu
 * @since 2017/12/18 16:14
 */
public class Seckill implements Serializable {

    @Column(length = 32)
    private String activityId;

    @Column(length = 32)
    private String userId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

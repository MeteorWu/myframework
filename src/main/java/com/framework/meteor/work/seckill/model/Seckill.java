package com.framework.meteor.work.seckill.model;

import com.framework.meteor.framework.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 秒杀
 *
 * @author Meteor.wu
 * @since 2017/12/18 16:14
 */
@Entity
public class Seckill extends BaseModel {

    @Id
    @Column(length = 32)
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

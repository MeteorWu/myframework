package com.framework.meteor.work.seckill.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Meteor.wu
 * @since 2017/12/29 13:28
 */
@Entity
public class Record {
    @Id
    @Column(length = 32)
    private String id;

    @Column(length = 32)
    private String activityId;

    @Column(length = 32)
    private String userId;

    public Record() {
    }

    public Record(String activityId, String userId) {
        this.activityId = activityId;
        this.userId = userId;
    }

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

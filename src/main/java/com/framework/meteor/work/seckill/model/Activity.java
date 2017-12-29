package com.framework.meteor.work.seckill.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 活动
 *
 * @author Meteor.wu
 * @since 2017/12/29 13:26
 */
@Entity
public class Activity {
    @Id
    @Column(length = 32)
    private String id;

    @Column(columnDefinition = "int")
    private Integer prizeCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPrizeCount() {
        return prizeCount;
    }

    public void setPrizeCount(Integer prizeCount) {
        this.prizeCount = prizeCount;
    }
}

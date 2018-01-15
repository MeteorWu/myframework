package com.framework.meteor.work.user.model;

import com.framework.meteor.framework.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Meteor.wu
 * @since 2018/1/15 16:40
 */
@Entity
public class Other extends BaseModel {
    @Id
    @Column(length = 32)
    private String otherId;

    @Column(length = 32)
    private String otherString;

    @Column(length = 32)
    private String userId;

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getOtherString() {
        return otherString;
    }

    public void setOtherString(String otherString) {
        this.otherString = otherString;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

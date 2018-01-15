package com.framework.meteor.work.user.model;

/**
 * @author Meteor.wu
 * @since 2018/1/15 16:37
 */

public class UserTestRecordDTO {
    private String userId;

    private String username;

    private String otherId;

    private String otherString;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOtherString() {
        return otherString;
    }

    public void setOtherString(String otherString) {
        this.otherString = otherString;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }
}

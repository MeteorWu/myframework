package com.framework.meteor.work.guavaEvent;

import lombok.Getter;

/**
 * @author Meteor.wu
 * @since 2018/2/6 10:51
 */

public class MyEvent {
    @Getter
    private String message;

    public MyEvent(String message) {
        this.message = message;
    }

//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}

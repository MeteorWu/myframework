package com.framework.meteor.work.guavaEvent;

import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Meteor.wu
 * @since 2018/2/6 10:48
 */
@Slf4j
public class ServiceListener {

    @Subscribe
    public void listen(MyEvent event) throws InterruptedException {
        String message = event.getMessage();
        log.info("ready show");
        Thread.sleep(3000L);
        log.info(message);

    }
}

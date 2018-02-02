package com.framework.meteor.work.reactorEventBusTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.bus.EventBus;

import static reactor.bus.selector.Selectors.$;

/**
 * 关联事件类型与事件处理程序
 * @author Meteor.wu
 * @since 2018/2/2 16:11
 */

@Component
public class ReactorEventBusLauncher implements CommandLineRunner {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private ReactorEventBusService reactorEventBusService;

    @Override
    public void run(String... strings) throws RuntimeException {
        eventBus.on($("theRoute"), reactorEventBusService);
    }
}

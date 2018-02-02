package com.framework.meteor.work.reactorEventBusTest;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import reactor.bus.Event;
import reactor.fn.Consumer;

/**
 * @author Meteor.wu
 * @since 2018/2/2 16:13
 */
@Slf4j
@Service
public class ReactorEventBusService implements Consumer<Event<JSONObject>> {
    @Override
    public void accept(Event<JSONObject> ev) {
        if (ev != null) {
            JSONObject data = ev.getData();
            if (data.containsKey("message")) {
                log.info(data.getString("message"));
            } else {
                log.info("no message");
            }
            log.info("event is over");
        }
    }
}

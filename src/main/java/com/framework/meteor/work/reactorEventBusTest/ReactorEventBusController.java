package com.framework.meteor.work.reactorEventBusTest;

import com.framework.meteor.framework.constant.ResultMsg;
import com.framework.meteor.framework.model.Response;
import com.framework.meteor.framework.model.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.bus.Event;
import reactor.bus.EventBus;

/**
 * @author Meteor.wu
 * @since 2018/2/2 16:07
 */
@RestController
@RequestMapping("/reactorEventBus")
@Slf4j
public class ReactorEventBusController {

    @Autowired
    EventBus eventBus;

    @PostMapping("/trigger")
    public Response getById(@RequestBody JSONObject json) {
        eventBus.notify("theRoute", Event.wrap(json));
        log.info("return 2 postman right now");
        return new ResponseBody<>(ResultMsg.SUCCESS);
    }
}

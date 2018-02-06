package com.framework.meteor.work.guavaEvent;

import com.framework.meteor.framework.constant.ResultMsg;
import com.framework.meteor.framework.model.Response;
import com.framework.meteor.framework.model.ResponseBody;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;

/**
 * @author Meteor.wu
 * @since 2018/2/6 10:47
 */
@RestController
@RequestMapping("/guavaEvent")
@Slf4j
public class GuavaEventController {


    @PostMapping("/trigger")
    public Response getById(@RequestBody JSONObject json) {
        EventBus eventBus = new EventBus("trigger");
        AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));

        ServiceListener serviceListener = new ServiceListener();
        asyncEventBus.register(serviceListener);

        asyncEventBus.post(new MyEvent(json.getString("message")));


        log.info("return to client");
        return new ResponseBody<>(ResultMsg.SUCCESS);
    }
}

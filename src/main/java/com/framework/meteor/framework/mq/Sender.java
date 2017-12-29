package com.framework.meteor.framework.mq;

import com.framework.meteor.work.seckill.model.Seckill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * 消息生产者
 * @author Meteor.wu
 * @since 2017/12/18 16:16
 */
@Slf4j
@Component
public class Sender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送成功:" + correlationData.toString());
        } else {
            System.out.println("消息发送失败:" + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println(message.getMessageProperties().getCorrelationIdString() + " 发送失败");
    }

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    public void send(Seckill seckill){

        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        Object ret = rabbitTemplate.convertSendAndReceive(RabbitmqConfig.SECKILL_FANOUT_EXCHANGE, RabbitmqConfig.SECKILL_QUEUE_NAME,seckill, correlationId);
        log.info("return message is " + ret);
        log.info("return message is " + ret);

    }
}

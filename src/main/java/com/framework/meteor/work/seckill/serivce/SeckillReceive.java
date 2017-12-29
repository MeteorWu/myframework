package com.framework.meteor.work.seckill.serivce;

import com.framework.meteor.framework.mq.RabbitmqConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 秒杀消费者
 *
 * @author Meteor.wu
 * @since 2017/12/18 16:40
 */
@Component
@Slf4j
public class SeckillReceive {

    @RabbitListener(queues = RabbitmqConfig.SECKILL_QUEUE_NAME, containerFactory="rabbitListenerContainerFactory")
    public void seckill(Message message, Channel channel) throws IOException {
        try {
            log.info("##### = {}", new String(message.getBody()));

            log.info("##### = {}", channel == null);
            if (channel != null) {
                log.info("id =  ", channel.getConnection().getId());
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//手动消息应答
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);//true 重新放入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//对于处理不了的异常消息

            //发送到失败队列
//            rabbitTemplate.convertAndSend(RabbitmqConfig.SECKILL_FANOUT_EXCHANGE, RabbitmqConfig.ROUTINGKEY_FAIL);
        }finally {

        }
    }

    @RabbitListener(queues = RabbitmqConfig.SECKILL_QUEUE_NAME_NOTICE, containerFactory="rabbitListenerContainerFactory")
    public void seckillNotice(Message message, Channel channel) throws IOException {
        System.out.println("notice = " + message.getBody().toString());
    }
}

package com.framework.meteor.work.seckill.serivce;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.meteor.framework.constant.BooleanEnum;
import com.framework.meteor.framework.mq.RabbitmqConfig;
import com.framework.meteor.work.seckill.model.Activity;
import com.framework.meteor.work.seckill.model.Record;
import com.framework.meteor.work.seckill.model.Seckill;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RecordService recordService;

    @Autowired
    private ActivityService activityService;

    @RabbitListener(queues = RabbitmqConfig.SECKILL_QUEUE_NAME, containerFactory="rabbitListenerContainerFactory")
    public Integer seckill(Message message, Channel channel ) throws IOException {
        try {
            ObjectMapper mapper=new ObjectMapper();
            Seckill seckill = mapper.readValue(message.getBody(), Seckill.class);

            Activity activity = activityService.getById(seckill.getActivityId());
            if (activity != null && activity.getPrizeCount() > 0) {
                activityService.seckillPrize(seckill.getActivityId());
                recordService.save(new Record(seckill.getActivityId(), seckill.getUserId()));
                log.info("the userId " + seckill.getUserId() + " seckill " + seckill.getActivityId());
                return BooleanEnum.TRUE.ordinal();
            }

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);//手动消息应答

        } catch (IOException e) {
            log.error(e.getMessage(), e);
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);//true 重新放入队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//对于处理不了的异常消息
        }
        return BooleanEnum.FALSE.ordinal();
    }

    @RabbitListener(queues = RabbitmqConfig.SECKILL_QUEUE_NAME_NOTICE, containerFactory="rabbitListenerContainerFactory")
    public void seckillNotice(Message message, Channel channel) throws IOException {
        System.out.println("notice message receive");
        recordService.save(new Record("", ""));
    }
}

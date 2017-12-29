package com.framework.meteor.framework.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置
 *
 * @author Meteor.wu
 * @since 2017/12/18 15:46
 */
@EnableRabbit
@Configuration
public class RabbitmqConfig {

    public static final String SECKILL_FANOUT_EXCHANGE = "seckillExchange";

    public static final String SECKILL_QUEUE_NAME = "seckillQueue";
    public static final String SECKILL_QUEUE_NAME_NOTICE = "seckillQueuenNotice";

    //RabbitMQ的配置信息
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;


    //    建立一个连接容器，类型数据库的连接池
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setPublisherConfirms(true);// 确认机制 //发布确认，template要求CachingConnectionFactory的publisherConfirms属性设置为true
        connectionFactory.setConnectionLimit(100);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(SECKILL_FANOUT_EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return new Queue(SECKILL_QUEUE_NAME, true); //队列持久
    }

    @Bean
    public Queue queueNotice() {
        return new Queue(SECKILL_QUEUE_NAME_NOTICE, true); //队列持久
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingNotice() {
        return BindingBuilder.bind(queueNotice()).to(fanoutExchange());
    }

    @Bean
    public SimpleMessageListenerContainer messageContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
        container.setQueues(queue());
        container.setExposeListenerChannel(true);
        container.setMaxConcurrentConsumers(1);
        container.setConcurrentConsumers(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
//        container.setMessageListener(new ChannelAwareMessageListener() {
//
//            @Override
//            public void onMessage(Message message, Channel channel) throws Exception {
//                byte[] body = message.getBody();
//                System.out.println("receive msg : " + new String(body));
//                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); //确认消息成功消费
//            }
//        });
        return container;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        factory.setAutoStartup(false);
        return factory;
    }
}

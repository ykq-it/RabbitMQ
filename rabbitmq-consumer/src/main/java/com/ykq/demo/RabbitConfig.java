package com.ykq.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RabbitConfig
 * @Description TODO
 * @Author ykq
 * @Date 2021/05/24
 * @Version v1.0.0
 */
@Slf4j
@Configuration
public class RabbitConfig {

    /** 无交换机direct */
    @Bean
    public Queue directQueue() {
        Map<String, Object> map = new HashMap<>();
//        map.put("x-delayed-message", 10000);
//        map.put("x-message-ttl", 10000);
        map.put("x-max-length", 5);
        map.put("x-dead-letter-exchange", Constant.dlExchange);
//        durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
//        exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
//        autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
//        return new Queue("TestDirectQueue",true,true,false);
//        一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue(Constant.directQueue, true, false, false, map);
//        return new Queue(Constant.directQueue);
    }

    /** 有交换机direct */
    @Bean
    public Queue directQueueE() {
        return new Queue(Constant.directQueueE);
    }
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(Constant.directExchange);
    }
    @Bean
    public Binding deBind(Queue directQueueE, DirectExchange directExchange) {
        return BindingBuilder.bind(directQueueE).to(directExchange).with(Constant.directRouteKey);
    }

    /** topic */
    @Bean
    public Queue topicQueue() {
        return new Queue(Constant.topicQueue);
    }
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(Constant.topicExchange);
    }
    @Bean
    public Binding teBind(Queue topicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with(Constant.topicRouteKey1);
    }
    @Bean
    public Binding teBind1(Queue topicQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicQueue).to(topicExchange).with(Constant.topicRouteKey2);
    }

    /** headers */
    @Bean
    public Queue headersQueue() {
        return new Queue(Constant.headersQueue);
    }
    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(Constant.headersExchange);
    }
    @Bean
    public Binding heBind(Queue headersQueue, HeadersExchange headersExchange) {
        Map<String, Object> headerValue = new HashMap<>();
        headerValue.put("type", 1);
        headerValue.put("name", 1);
        // 只要有一个匹配就行
//        return BindingBuilder.bind(headersQueue).to(headersExchange).whereAny(headerValue).match();
        // 必须全部匹配，注意如果需要验证，必须删除上面whereAny规则的同名的queue，否则whereAll不生效
        return BindingBuilder.bind(headersQueue).to(headersExchange).whereAll(headerValue).match();
    }

    /**
     * fanout
     * FanoutExchange交换机是转发消息到所有绑定队列（广播模式，和routingKey没有关系）
     * 发送时不需要设置routingKey
     * 只要是绑定到广播交换机的队列，都能收到消息
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(Constant.fanoutQueue1);
    }
    @Bean
    public Queue fanoutQueue2() {
        return new Queue(Constant.fanoutQueue2);
    }
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(Constant.fanoutExchange);
    }
    @Bean
    public Binding feBind1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }
    @Bean
    public Binding feBind2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }

    /** Dead Letter */
    @Bean
    public Queue dlQueue() {
        return new Queue(Constant.dlQueue);
    }
    @Bean
    public TopicExchange dlExchange() {
        return new TopicExchange(Constant.dlExchange);
    }
    @Bean
    public Binding dleBind(Queue dlQueue, TopicExchange dlExchange) {
        return BindingBuilder.bind(dlQueue).to(dlExchange).with(Constant.dlRouteKey);
    }

    /**
     * 配置启用rabbitmq事务，有交换机direct
     */
    @Bean
    public Queue txToTxQueue() {
        return new Queue(Constant.txToTxQueue);
    }
    @Bean
    public DirectExchange txToTxExchange() {
        return new DirectExchange(Constant.txToTxExchange);
    }
    @Bean
    public Binding txToTxBind(Queue txToTxQueue, DirectExchange txToTxExchange) {
        return BindingBuilder.bind(txToTxQueue).to(txToTxExchange).with(Constant.txToTxRouteKey);
    }
//    @Bean("rabbitTransactionManager")
//    public RabbitTransactionManager rabbitTransactionManager(CachingConnectionFactory connectionFactory) {
//        return new RabbitTransactionManager(connectionFactory);
//    }

//    /**
//     * 生产者确认
//     */
//    @Autowired
//    private CachingConnectionFactory cachingConnectionFactory;
//
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        // 若使用confirm-callback，必须要配置publisherConfirm为true
//        cachingConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
//        // 若使用return-callback，必须要配置publisherReturns为true
//        cachingConnectionFactory.setPublisherReturns(true);
//
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
//        // 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true
//        rabbitTemplate.setMandatory(true);
//
//        // 如果消息没有到exchange，则confirm回调，ack=false；如果消息到达exchange，则confirm回调，ack=true
//        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
//            @Override
//            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//                if (ack) {
//                    log.info("消息发送到exchange成功：correlationData（{}），ack（{}），cause（{}）", correlationData, ack, cause);
//                } else {
//                    log.info("消息发送到exchange失败：correlationData（{}），ack（{}），cause（{}）", correlationData, ack, cause);
//                }
//            }
//        });
//
//        // 如果exchange到达queue成功，则不回调return；如果exchange到达queue失败，则回调return（需设置mandatory=true，否则不会回调——消息丢失）
//        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
//            @Override
//            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//                log.info("消息发送到queue丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
//            }
//        });
//        return rabbitTemplate;
//    }

}

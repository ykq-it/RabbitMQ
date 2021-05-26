package com.ykq.demo;

import org.springframework.amqp.core.*;
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
@Configuration
public class RabbitConfig {

    /** 无交换机direct */
    @Bean
    public Queue directQueue() {
        Map<String, Object> map = new HashMap<>();
//        map.put("x-delayed-message", 10000);
        map.put("x-message-ttl", 10000);
        map.put("x-dead-letter-exchange", Constant.dlExchange);
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
}

package com.ykq.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    /**
     * 生产者确认
     */
    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Bean
    public RabbitTemplate rabbitTemplate() {
        // 若使用confirm-callback，必须要配置publisherConfirm为true
        cachingConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        // 若使用return-callback，必须要配置publisherReturns为true
        cachingConnectionFactory.setPublisherReturns(true);

        RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
        // 使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true
        rabbitTemplate.setMandatory(true);

        // 如果消息没有到exchange，则confirm回调，ack=false；如果消息到达exchange，则confirm回调，ack=true
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack) {
                    log.info("消息发送到exchange成功：correlationData（{}），ack（{}），cause（{}）", correlationData, ack, cause);
                } else {
                    log.info("消息发送到exchange失败：correlationData（{}），ack（{}），cause（{}）", correlationData, ack, cause);
                }
            }
        });

        // 如果exchange到达queue成功，则不回调return；如果exchange到达queue失败，则回调return（需设置mandatory=true，否则不会回调——消息丢失）
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息发送到queue丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
            }
        });
        return rabbitTemplate;
    }

}

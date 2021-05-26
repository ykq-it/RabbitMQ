package com.ykq.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @ClassName Producer
 * @Description TODO
 * @Author ykq
 * @Date 2021/05/24
 * @Version v1.0.0
 */
@Slf4j
@Component
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /** 发送时指定过期配置 */
    public void sender(String s) {
        log.info(Constant.directQueue + ": " + s);
//        MessageProperties properties = new MessageProperties();
//        properties.setExpiration("10000");  // 单位ms
//        amqpTemplate.convertAndSend(Constant.directQueue, new Message(s.getBytes(StandardCharsets.UTF_8), properties));
        amqpTemplate.convertAndSend(Constant.directQueue, s);
    }

    /** 直接用queue即可 */
    public void sender1(String s) {
        log.info(Constant.topicQueue + ": " + s);
        amqpTemplate.convertAndSend(Constant.topicQueue, s);
    }

    /** 指定交换机，* */
    public void sender2(String s) {
        log.info(Constant.topicQueue + ": " + s);
        // 交换机，路由键
        amqpTemplate.convertAndSend(Constant.topicExchange, Constant.topicRouteKey1, s);
    }

    /** 指定交换机，# */
    public void sender3(String s) {
        log.info(Constant.topicQueue + ": " + s);
        // 交换机，路由键
        amqpTemplate.convertAndSend(Constant.topicExchange, Constant.topicRouteKey2, s);
    }

    /** 指定header交换机，# */
    public void sender4(Map<String, Object> header, String s) {
        log.info(Constant.headersQueue + ": " + s);
        // 交换机，路由键
        amqpTemplate.convertAndSend(Constant.headersExchange, Constant.headersQueue, getMessage(header, s));
    }

    private Object getMessage(Map<String, Object> header, String s) {
        MessageProperties properties = new MessageProperties();
        for (Map.Entry<String, Object> entry : header.entrySet()) {
            properties.setHeader(entry.getKey(), entry.getValue());
        }

        Message message = new Message(s.getBytes(StandardCharsets.UTF_8), properties);
        return message;
    }

    public void sender5(String s) {
        log.info(Constant.fanoutExchange + ": " + s);
        // 交换机，路由键
        amqpTemplate.convertAndSend(Constant.fanoutExchange, "", s);
    }
}

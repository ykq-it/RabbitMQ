package com.ykq.web;

import com.ykq.demo.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RabbitMQ消息发送类
 */
@Slf4j
@Component
public class RabbitSender {

    /***
     * AmqpTemplate是RabbitTemplate的接口，RabbitTemplate实现RabbitOperations接口，
     * RabbitOperations继承了AmqpTemplate接口，所以这里可以使用AmqpAmqpTemplate
     */
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendTxQueue(String msg) {
        log.info(Constant.txToTxQueue + "：" + msg);
        amqpTemplate.convertAndSend(Constant.txToTxExchange, Constant.txToTxRouteKey, msg);
    }

    public void sendTxQueue1(String msg) {
        log.info(Constant.txToTxQueue + "：" + msg);
        amqpTemplate.convertAndSend(Constant.txToTxExchange, Constant.txToTxRouteKey, msg);
    }


}
package com.ykq.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ClassName Consumer
 * @Description TODO
 * @Author ykq
 * @Date 2021/05/24
 * @Version v1.0.0
 */
@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = "${mq.directQueue}")
    public void receive(String s) {
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info(Constant.directQueue + ": " + s);
    }

    @RabbitListener(queues = "${mq.topicQueue}")
    public void receive1(String s, Channel channel) {
        log.info(Constant.topicQueue + ": " + s);
//        throw new RuntimeException("123");
//        try {
//            Thread.sleep(10000);
//            channel.basicAck(100L, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @RabbitListener(queues = "${mq.headersQueue}")
    public void receive2(String s) {
        log.info(Constant.headersQueue + ": " + s);
    }

    /** fanout */
    @RabbitListener(queues = "${mq.fanoutQueue1}")

    public void receive3(String s) {
        log.info(Constant.fanoutQueue1 + ": " + s);
    }
    @RabbitListener(queues = "${mq.fanoutQueue2}")
    public void receive4(String s) {
        log.info(Constant.fanoutQueue2 + ": " + s);
    }

    /** dead letter */
    @RabbitListener(queues = "${mq.dlQueue}")
    public void receive5(String s) {
        log.info(Constant.dlQueue + ": " + s);
    }

    /** 事务 */
    @RabbitListener(queues = "${mq.txToTxQueue}")
    public void processDirect(String s, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws Exception {
        /* 基础ack验证 */
//        log.info(Constant.txToTxQueue + ": " + s + "deliveryTag: " + deliveryTag);

        /* 验证ack单条确认 */
//        log.info(Constant.txToTxQueue + ": " + s + "deliveryTag: " + deliveryTag);
//        channel.basicAck(deliveryTag, false);

        /* 验证ack批量确认 */
//        log.info(Constant.txToTxQueue + ": " + s + "deliveryTag: " + deliveryTag);
//        if (5 == deliveryTag) {
//            channel.basicAck(deliveryTag, true);
//        }

        /* 验证nack批量拒绝，且重回队列 */
//        log.info(Constant.txToTxQueue + ": " + s + "deliveryTag: " + deliveryTag);
//        if (5 == deliveryTag) {
//            channel.basicNack(deliveryTag, true, true);
//        }

        /* 验证reject单条拒绝，且重回队列 */
//        log.info(Constant.txToTxQueue + ": " + s + "deliveryTag: " + deliveryTag);
//        channel.basicReject(deliveryTag, true);

        /* AUTO ack验证异常重回队列 */
//        log.info(Constant.txToTxQueue + ": " + s + "deliveryTag: " + deliveryTag);
//        throw new Exception("运行时异常");
    }
}

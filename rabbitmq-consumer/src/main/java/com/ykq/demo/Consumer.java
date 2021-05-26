package com.ykq.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

//    @RabbitListener(queues = "${mq.directQueue}")
//    public void receive(String s) {
//        log.info(Constant.directQueue + ": " + s);
//    }

    @RabbitListener(queues = "${mq.topicQueue}")
    public void receive1(String s) {
        log.info(Constant.topicQueue + ": " + s);
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
}

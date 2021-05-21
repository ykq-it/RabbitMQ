package com.ykq.demo.dirct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Consumer
 * @Description TODO
 * @Author ykq
 * @Date 2021/05/21
 * @Version v1.0.0
 */
@Slf4j
@Component
public class Consumer {

    @RabbitHandler
    @RabbitListener(queues = "ykq.direct")
    public void receive(String s) {
        log.info("direct接收：" + s);
    }
}

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

    @RabbitListener(queues = "${mq.directQueue}")
    public void receive(String s) {
        log.info(Constant.directQueue + ": " + s);
    }
}

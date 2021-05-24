package com.ykq.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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

    public void sender(String s) {
        log.info(Constant.directQueue + ": " + s);
        amqpTemplate.convertAndSend(Constant.directQueue, s);
    }
}

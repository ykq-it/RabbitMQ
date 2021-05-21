package com.ykq.demo.dirct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName Product
 * @Description TODO
 * @Author ykq
 * @Date 2021/05/21
 * @Version v1.0.0
 */
@Slf4j
@Service
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String s) {
        amqpTemplate.convertAndSend("ykq.direct", s);
    }
}

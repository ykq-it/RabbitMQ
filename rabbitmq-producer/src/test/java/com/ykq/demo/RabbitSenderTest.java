package com.ykq.demo;

import com.ykq.web.RabbitSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitSenderTest {

    @Autowired
    private RabbitSender rabbitSender;

    @Test
    public void sendTxQueue() {
        rabbitSender.sendTxQueue("234");
    }
}
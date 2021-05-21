package com.ykq;

import com.ykq.demo.dirct.Producer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class RabbitMqProducerApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    void send() {
        producer.send("消息");
    }
}

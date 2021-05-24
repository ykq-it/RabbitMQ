package com.ykq;

import com.ykq.demo.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqProducerApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    void send() {
        producer.sender("123");
    }

}

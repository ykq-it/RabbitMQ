package com.ykq;

import com.ykq.demo.Producer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RabbitmqProducerApplicationTests {

    @Autowired
    private Producer producer;

    @Test
    void send() {
//        for (int i = 0; i < 60; i++) {
            producer.sender("123");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Test
    void send1() {
        producer.sender1("123");
    }

    @Test
    void send2() {
        producer.sender2("123");
    }

    @Test
    void send3() {
        producer.sender3("123");
    }

    @Test
    void send4() {
        Map<String, Object> head = new HashMap<>();
        head.put("type", 1);
        head.put("name", 1);
        producer.sender4(head, "123");
    }

    @Test
    void send5() {
        producer.sender5("123");
    }
}

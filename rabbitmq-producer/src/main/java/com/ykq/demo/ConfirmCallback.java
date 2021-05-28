package com.ykq.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConfirmCallback
 * @Description TODO
 * @Author ykq
 * @Date 2021/05/26
 * @Version v1.0.0
 */
@Slf4j
@Component
public class ConfirmCallback implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("发送成功，correlationData={}，cause={}", correlationData, cause);
        } else {
            log.info("发送失败，correlationData={}，cause={}", correlationData, cause);
        }
    }
}

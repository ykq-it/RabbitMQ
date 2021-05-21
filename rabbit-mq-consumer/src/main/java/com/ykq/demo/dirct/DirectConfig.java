package com.ykq.demo.dirct;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName DirectConfig
 * @Description TODO
 * @Author ykq
 * @Date 2021/05/21
 * @Version v1.0.0
 */
@Configuration
public class DirectConfig {

    @Bean
    public Queue directQueue() {
        return new Queue("ykq.direct");
    }
}
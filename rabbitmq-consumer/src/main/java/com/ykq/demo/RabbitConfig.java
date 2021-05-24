package com.ykq.demo;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitConfig
 * @Description TODO
 * @Author ykq
 * @Date 2021/05/24
 * @Version v1.0.0
 */
@Configuration
public class RabbitConfig {

    /** 无交换机direct */
    @Bean
    public Queue directQueue() {
        return new Queue(Constant.directQueue);
    }

    /** 有交换机direct */
    @Bean
    public Queue directQueueE() {
        return new Queue(Constant.directQueueE);
    }
    @Bean
    public Exchange directExchange() {
        return new DirectExchange(Constant.directExchange);
    }
//    @Bean
//    public Binding deBind() {
//        return
//    }


}

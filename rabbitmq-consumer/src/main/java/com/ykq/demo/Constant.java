package com.ykq.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName Constant
 * @Description 使用配置类装配静态参数，光使用ConfigurationProperties是不够的，要加上非静态的set方法才可以
 * @Author ykq
 * @Date 2021/05/24
 * @Version v1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "mq")
public class Constant {
    public static String directQueue;
    public static String directQueueE;
    public static String directExchange;

    public void setDirectExchange(String directExchange) {
        Constant.directExchange = directExchange;
    }

    public void setDirectQueue(String directQueue) {
        Constant.directQueue = directQueue;
    }

    public void setDirectQueueE(String directQueueE) {
        Constant.directQueueE = directQueueE;
    }
}

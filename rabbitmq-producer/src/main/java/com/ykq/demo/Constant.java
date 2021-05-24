package com.ykq.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @ClassName Constant
 * @Description TODO
 * @Author ykq
 * @Date 2021/05/24
 * @Version v1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "mq")
public class Constant {
    public static String directQueue;

    public void setDirectQueue(String directQueue) {
        Constant.directQueue = directQueue;
    }
}
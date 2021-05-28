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
    public static String directRouteKey;
    public static String topicQueue;
    public static String topicExchange;
    public static String topicRouteKey1;
    public static String topicRouteKey2;
    public static String headersQueue;
    public static String fanoutQueue1;
    public static String fanoutQueue2;
    public static String fanoutExchange;
    public static String dlQueue;
    public static String dlExchange;
    public static String dlRouteKey;
    public static String txToTxQueue;
    public static String txToTxExchange;
    public static String txToTxRouteKey;

    public void setTxToTxQueue(String txToTxQueue) {
        Constant.txToTxQueue = txToTxQueue;
    }

    public void setTxToTxExchange(String txToTxExchange) {
        Constant.txToTxExchange = txToTxExchange;
    }

    public void setTxToTxRouteKey(String txToTxRouteKey) {
        Constant.txToTxRouteKey = txToTxRouteKey;
    }

    public void setDlQueue(String dlQueue) {
        Constant.dlQueue = dlQueue;
    }

    public void setDlExchange(String dlExchange) {
        Constant.dlExchange = dlExchange;
    }

    public void setDlRouteKey(String dlRouteKey) {
        Constant.dlRouteKey = dlRouteKey;
    }

    public void setFanoutQueue1(String fanoutQueue1) {
        Constant.fanoutQueue1 = fanoutQueue1;
    }

    public void setFanoutQueue2(String fanoutQueue2) {
        Constant.fanoutQueue2 = fanoutQueue2;
    }

    public void setFanoutExchange(String fanoutExchange) {
        Constant.fanoutExchange = fanoutExchange;
    }

    public void setHeadersQueue(String headersQueue) {
        Constant.headersQueue = headersQueue;
    }

    public void setHeadersExchange(String headersExchange) {
        Constant.headersExchange = headersExchange;
    }

    public static String headersExchange;

    public void setTopicQueue(String topicQueue) {
        Constant.topicQueue = topicQueue;
    }

    public void setTopicExchange(String topicExchange) {
        Constant.topicExchange = topicExchange;
    }

    public void setTopicRouteKey1(String topicRouteKey1) {
        Constant.topicRouteKey1 = topicRouteKey1;
    }

    public void setTopicRouteKey2(String topicRouteKey2) {
        Constant.topicRouteKey2 = topicRouteKey2;
    }

    public void setDirectRouteKey(String directRouteKey) {
        Constant.directRouteKey = directRouteKey;
    }

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

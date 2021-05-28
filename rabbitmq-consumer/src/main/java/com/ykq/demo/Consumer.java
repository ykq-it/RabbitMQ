package com.ykq.demo;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @ClassName Consumer
 * @Description TODO
 * @Author ykq
 * @Date 2021/05/24
 * @Version v1.0.0
 */
@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = "${mq.directQueue}")
    public void receive(String s) {
//        try {
//            Thread.sleep(60000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info(Constant.directQueue + ": " + s);
    }

    @RabbitListener(queues = "${mq.topicQueue}")
    public void receive1(String s, Channel channel) {
        log.info(Constant.topicQueue + ": " + s);
//        throw new RuntimeException("123");
//        try {
//            Thread.sleep(10000);
//            channel.basicAck(100L, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @RabbitListener(queues = "${mq.headersQueue}")
    public void receive2(String s) {
        log.info(Constant.headersQueue + ": " + s);
    }

    /** fanout */
    @RabbitListener(queues = "${mq.fanoutQueue1}")
    public void receive3(String s) {
        log.info(Constant.fanoutQueue1 + ": " + s);
    }
    @RabbitListener(queues = "${mq.fanoutQueue2}")
    public void receive4(String s) {
        log.info(Constant.fanoutQueue2 + ": " + s);
    }

    /** dead letter */
    @RabbitListener(queues = "${mq.dlQueue}")
    public void receive5(String s) {
        log.info(Constant.dlQueue + ": " + s);
    }

    /** 事务 */
    @RabbitListener(queues = "${mq.txToTxQueue}")
    public void processDirect(String s, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
//        try{
            log.info(Constant.txToTxQueue + ": " + s);
            // TODO：接收到消息进行业务操作，操作成功，告诉mq该消息已经消费
            // 执行业务操作，同一个数据不能处理两次，根据业务情况去重，保证幂等性。 （拓展：redis记录处理情况）
            // 开启了手工确认机制，如果不加这个，项目重新启动，则改消息会被重新消费
            // 异常的话，可以选择让它重新入列，或者丢弃
//            channel.basicAck(tag,true);
//        }catch (Exception e){
            // 异常情况 :根据需要去： 重发/ 丢弃
            // 重发一定次数后， 丢弃， 日志告警,b1:true表示重新入列，false表示不入列
//            channel.basicNack(tag, false, true);
            // 系统 关键数据，永远是有人工干预
//        }
    }
}

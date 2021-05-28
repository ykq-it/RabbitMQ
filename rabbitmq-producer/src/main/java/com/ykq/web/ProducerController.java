package com.ykq.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName ProducerController
 * @Description TODO
 * @Author ykq
 * @Date 2021/05/27
 * @Version v1.0.0
 */
@Slf4j
@Controller
public class ProducerController {

    @Autowired
    private RabbitSender rabbitSender;

    @ResponseBody
    @GetMapping("/tx")
    public String tx() {
        rabbitSender.sendTxQueue("111");
        return "OK";
    }
}

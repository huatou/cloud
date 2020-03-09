package com.zigar.rabbitmq.rest;

import com.zigar.rabbitmq.service.ReceiveService;
import com.zigar.rabbitmq.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zigar
 * @createTime 2020-02-22 13:36:28
 * @description
 */

@RestController
@EnableBinding({SendService.class, ReceiveService.class})
public class TestRestController {

    @Autowired
    SendService sendService;

    @Autowired
    ReceiveService receiveService;


    @RequestMapping("/test")
    Object test() {
        Message build = MessageBuilder.withPayload("hello,rabbitmq".getBytes()).build();

        Boolean send = sendService.sendMember().send(build);

        return send.toString();
    }


    @StreamListener("member")
    void receive(byte msg[]) {
        System.out.println(new String(msg));
    }


}

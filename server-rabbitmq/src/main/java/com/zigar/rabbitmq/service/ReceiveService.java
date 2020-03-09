package com.zigar.rabbitmq.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ReceiveService {
 
    @Input("member")
    SubscribableChannel subscribableChannel();
}
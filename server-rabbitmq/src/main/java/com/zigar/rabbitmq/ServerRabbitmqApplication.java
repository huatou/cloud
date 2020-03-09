package com.zigar.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Zigar
 * @createTime 2020-01-15 11:21:06
 * @description
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.zigar"})
@EnableEurekaClient
public class ServerRabbitmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerRabbitmqApplication.class, args);
    }
}

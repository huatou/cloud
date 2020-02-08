package com.zigar.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka注册中心
 */
@SpringBootApplication
@EnableEurekaServer //申明这是一个Eureka服务
public class AppEureka8100 {

   public static void main(String[] args) {
      SpringApplication.run(AppEureka8100.class, args);
   }
}

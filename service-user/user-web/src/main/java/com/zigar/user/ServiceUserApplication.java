package com.zigar.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Zigar
 * @createTime 2020-01-15 11:21:06
 * @description
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.zigar.user.config", "com.zigar.user.controller", "com.zigar.user.system"})
@EnableEurekaClient
@EnableOAuth2Sso
@EnableResourceServer
public class ServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }

}

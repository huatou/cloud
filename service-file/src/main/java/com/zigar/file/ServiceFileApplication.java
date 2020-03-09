package com.zigar.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Zigar
 * @createTime 2020-01-15 11:21:06
 * @description
 */

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableOAuth2Sso
@EnableResourceServer
public class ServiceFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFileApplication.class, args);
    }
}

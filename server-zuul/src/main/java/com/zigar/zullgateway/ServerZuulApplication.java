package com.zigar.zullgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableZuulProxy
@SpringBootApplication
//@ComponentScan("com.zigar.zullgateway.system.filter")
@EnableEurekaClient
@EnableOAuth2Sso
@EnableResourceServer
public class ServerZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerZuulApplication.class, args);
    }
}
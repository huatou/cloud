package com.zigar.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author Zigar
 * @createTime 2020-03-02 21:27:07
 * @description
 */

@SpringBootApplication
@EnableHystrixDashboard
@EnableEurekaClient
public class ServerDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerDashboardApplication.class, args);
    }
}

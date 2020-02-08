package com.zigar.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Zigar
 * @createTime 2020-01-15 11:21:06
 * @description
 */

@SpringBootApplication
@MapperScan(value = "com.zigar.file.mapper")
@ComponentScan(basePackages = {"com.zigar"})
@EnableEurekaClient
@EnableHystrix
@EnableFeignClients(basePackages ="com.zigar.file.feign")
@EnableDiscoveryClient
public class FileApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}

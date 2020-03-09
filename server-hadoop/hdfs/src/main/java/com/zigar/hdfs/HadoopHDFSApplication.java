package com.zigar.hdfs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Zigar
 * @createTime 2020-01-15 11:21:06
 * @description
 */

@SpringBootApplication
@ComponentScan("com.zigar")
public class HadoopHDFSApplication {

    public static void main(String[] args) {
        SpringApplication.run(HadoopHDFSApplication.class, args);
    }
}

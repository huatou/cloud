package com.zigar.file.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zigar
 * @createTime 2020-03-07 13:51:37
 * @description
 */

@Configuration
@ComponentScan({"com.zigar.file.config", "com.zigar.file.system", "com.zigar.file.service", "com.zigar.file.controller"})
@MapperScan("com.zigar.file.mapper")
public class AutoConfiguration {


}

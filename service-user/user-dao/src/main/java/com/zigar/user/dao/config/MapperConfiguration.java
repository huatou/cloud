package com.zigar.user.dao.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zigar
 * @createTime 2020-03-07 12:34:11
 * @description
 */

@Configuration
@MapperScan("com.zigar.user.dao")
public class MapperConfiguration {
}

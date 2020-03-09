package com.zigar.api.system.log;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zigar
 * @createTime 2020-03-06 20:15:12
 * @description
 */


@Configuration
public class LogConfiguration {

    @Bean
    LogRecordAspect logRecordAspect() {
        return new LogRecordAspect();
    }

}

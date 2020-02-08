package com.zigar.user.system.adapter.jsonadapter;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * 实体返回json时localDateTime格式校正
 */
@Configuration
public class LocalDateTimeSerializerConfig {

    // 方案一
    @Bean
    public LocalDateTimeSerializer localDateTimeDeserializer(JacksonProperties jacksonProperties) {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(jacksonProperties.getDateFormat()));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(LocalDateTimeSerializer localDateTimeDeserializer) {
        return builder -> builder.serializerByType(LocalDateTime.class, localDateTimeDeserializer);
    }

}
package com.zigar.file.adapter.jsonadapter;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * jackson处理LocalDateTime
 */
@Configuration
public class LocalDateTimeSerializerConfig {


    /**
     * 正常情况下jackson只能将LocalDateTime转为"2018-09-20T08:01:00"
     * 用于处理格式为LocalDateTime转为"2019-12-03 12:00:00"
     *
     * @param jacksonProperties
     * @return
     */
    @Bean
    public LocalDateTimeSerializer localDateTimeserializer(JacksonProperties jacksonProperties) {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(jacksonProperties.getDateFormat()));
    }

    /**
     * 正常情况下jackson只能将"2018-09-20T08:01:00"转成LocalDateTime
     * 用于处理格式为"2019-12-03 12:00:00"转为LocalDateTime
     *
     * @param jacksonProperties
     * @return
     */
    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer(JacksonProperties jacksonProperties) {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(jacksonProperties.getDateFormat()));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(LocalDateTimeSerializer localDateTimeSerializer, LocalDateTimeDeserializer localDateTimeDeserializer) {
        return builder -> builder
                .serializerByType(LocalDateTime.class, localDateTimeSerializer)
                .deserializerByType(LocalDateTime.class, localDateTimeDeserializer);
    }

}
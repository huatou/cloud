package com.zigar.file.system.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({FileProperties.class})
public class PropertiesConfiguration {
}
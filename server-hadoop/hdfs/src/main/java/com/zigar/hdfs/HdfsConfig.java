package com.zigar.hdfs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HdfsConfig {

    private String defaultHdfsUri = "hdfs://192.168.0.9:9000";

    @Bean
    public HdfsService hdfsService() {
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        conf.set("fs.defaultFS", defaultHdfsUri);
        return new HdfsService(conf, defaultHdfsUri);
    }
}
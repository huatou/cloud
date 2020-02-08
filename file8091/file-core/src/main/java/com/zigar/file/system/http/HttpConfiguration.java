package com.zigar.file.system.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author Zigar
 * @createTime 2020-01-28 23:13:50
 * @description
 */

@Configuration
public class HttpConfiguration {


    @Autowired
    private MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter;

//    @Autowired
//    private ClientHttpRequestFactory clientHttpRequestFactory;

    @Autowired
    private RequestResponseLoggingInterceptor requestResponseLoggingInterceptor;

//    @Bean
//
//    public RestTemplate restTemplate() {
//        RestTemplate restTemplate = new RestTemplate(new OkHttp3ClientHttpRequestFactory());
//        restTemplate.setMessageConverters(Collections.singletonList(mappingJacksonHttpMessageConverter));
//        return restTemplate;
//
//    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

//        restTemplate.setRequestFactory(new OkHttp3ClientHttpRequestFactory());
//        restTemplate.setMessageConverters(Collections.singletonList(mappingJacksonHttpMessageConverter));

//        restTemplate.setInterceptors(Collections.singletonList(requestResponseLoggingInterceptor));

        return restTemplate;
    }

}

package com.fgrapp.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * ApplicationContextConfig
 *
 * @author fan guang rui
 * @date 2020年06月13日 20:23
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced //使用LoadBalanced注解赋予了Rest Template负载均衡的能力
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}

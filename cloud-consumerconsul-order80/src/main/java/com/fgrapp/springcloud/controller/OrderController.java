package com.fgrapp.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import sun.plugin2.message.GetAppletMessage;

import javax.annotation.Resource;

/**
 * OrderController
 *
 * @author fan guang rui
 * @date 2020年06月20日 8:53
 */
@RestController
@Slf4j
public class OrderController {
    public static final String INVOKE_URL = "http://consul-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("consumer/payment/consul")
    public String paymentInfo(){
        return restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
    }
}

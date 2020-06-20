package com.fgrapp.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * PaymentMain
 *
 * @author fan guang rui
 * @date 2020年06月20日 8:35
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain.class,args);
    }
}

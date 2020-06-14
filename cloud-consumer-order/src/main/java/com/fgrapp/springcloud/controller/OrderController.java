package com.fgrapp.springcloud.controller;

import com.fgrapp.springcloud.entities.CommonResult;
import com.fgrapp.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * OrderController
 *
 * @author fan guang rui
 * @date 2020年06月13日 20:20
 */
@RestController
@Slf4j
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001"; //单机版
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE"; //集群版
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"payment/create",payment,CommonResult.class);
    }

    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("consumer/payment/discovery")
    public CommonResult getPaymentDiscovery(){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/discovery",CommonResult.class);
    }

    @GetMapping("/consumer/discovery")
    public CommonResult discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(element->{
            log.info("*****element:{}",element);
        });

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-ORDER-SERVICE");
        instances.forEach(serviceInstance -> {
            log.info(serviceInstance.getServiceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri());
        });
        return new CommonResult(200,"查询成功",discoveryClient);
    }
}

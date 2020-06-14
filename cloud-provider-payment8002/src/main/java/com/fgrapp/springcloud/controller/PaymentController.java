package com.fgrapp.springcloud.controller;

import com.fgrapp.springcloud.entities.CommonResult;
import com.fgrapp.springcloud.entities.Payment;
import com.fgrapp.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author fan guang rui
 * @date 2020年06月13日 18:11
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private int port;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);
        log.info("*****插入结果："+result);

        if(result > 0)
        {
            return new CommonResult(200,"插入数据库成功: port="+port,result);
        }else{
            return new CommonResult(444,"插入数据库失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);

        if(payment != null)
        {
            return new CommonResult(200,"查询成功:  port="+port,payment);
        }else{
            return new CommonResult(444,"没有对应记录,查询ID: "+id);
        }
    }

    @GetMapping("/payment/discovery")
    public CommonResult discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(element->{
            log.info("*****element:{}",element);
        });

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(serviceInstance -> {
            log.info(serviceInstance.getServiceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri());
        });
        return new CommonResult(200,"查询成功",discoveryClient);
    }
}

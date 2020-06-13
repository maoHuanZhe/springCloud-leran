package com.fgrapp.springcloud.service;

import com.fgrapp.springcloud.dao.PaymentDao;
import com.fgrapp.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @author fan guang rui
 * @date 2020年06月13日 18:10
 */
@Service
public class PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id){
        return paymentDao.getPaymentById(id);
    }
}

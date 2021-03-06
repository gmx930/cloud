package com.gmx.springcloud.controller;

import com.gmx.springcloud.entities.CommonResult;
import com.gmx.springcloud.entities.Payment;
import com.gmx.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Mingxiang Guo
 * @Date: 2020/10/7 20:12
 * @Version: 1.0
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult createPayment(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200, "插入数据成功,serverPort" + serverPort, result);
        } else {
            return new CommonResult(444, "插入数据失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment paymentById = paymentService.getPaymentById(id);
        if (paymentById != null) {
            return new CommonResult(200, "查询成功哈哈,serverPort:" + serverPort, paymentById);
        } else {
            return new CommonResult(444, "没有对应记录，查询id：" + id, null);
        }
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }

}

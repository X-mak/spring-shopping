package com.shopping.inferior.management.pay.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.shopping.common.Result;
import com.shopping.inferior.management.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

@RestController
@RequestMapping("/payment")
public class PayController {

    @GetMapping("")
    public String payment(@RequestParam String orderId) throws Exception {
        String s = payService.goAliPay(orderId);
        return s;
    }

    @PostMapping("backAlipay")
    public void alipay(HttpServletRequest req,
                       HttpServletResponse resp) throws Exception {
        payService.alipay(req, resp);
    }

    @Autowired
    PayService payService;
}

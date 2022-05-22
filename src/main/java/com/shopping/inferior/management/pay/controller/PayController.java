package com.shopping.inferior.management.pay.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.shopping.common.Result;
import com.shopping.inferior.management.pay.service.PayService;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/payment")
public class PayController {
    /**
     * 支付宝支付接口
     * @param orderId 订单编号
     */
    @ApiDoc(result = Result.class)
    @GetMapping("/ali")
    public String aliPayment(@RequestParam String orderId) throws Exception {
        String s = payService.goAliPay(orderId);
        return s;
    }

    @PostMapping("/backAlipay")
    public void alipay(HttpServletRequest req,
                       HttpServletResponse resp) throws Exception {
        payService.alipay(req, resp);
    }

    /**
     * 银联支付接口
     * @param orderId 订单编号
     * @param resp request
     */
    @ApiDoc(result = Result.class)
    @GetMapping("/union")
    public String pay(@RequestParam("orderId")String orderId,
                      HttpServletResponse resp)throws IOException{
        return payService.unionPayment(orderId, resp);
    }

    @PostMapping("/union/frontRcvResponse")
    public void unionBack(HttpServletRequest req,
                          HttpServletResponse resp) throws IOException, ServletException{
        payService.goUnionPay(req, resp);
    }

    @PostMapping("/union/backRcvResponse")
    public void unionPay(HttpServletRequest req,
                         HttpServletResponse resp) throws IOException, ServletException{
        payService.backUnionPay(req, resp);
    }

    @Autowired
    PayService payService;
}

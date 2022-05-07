package com.shopping.inferior.management.pay.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PayService {

    String goAliPay(String orderId) throws Exception;

    public String alipay(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

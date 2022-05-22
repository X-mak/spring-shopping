package com.shopping.inferior.management.pay.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface PayService {

    String goAliPay(String orderId) throws Exception;

    String alipay(HttpServletRequest request, HttpServletResponse response) throws Exception;

    void goUnionPay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    void backUnionPay(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

    String unionPayment(String orderId,HttpServletResponse resp)throws IOException;
}

package com.shopping.inferior.management.pay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.shopping.config.AliPayResource;
import com.shopping.entity.management.OrderItem;
import com.shopping.entity.management.Orders;
import com.shopping.mapper.management.OrderItemMapper;
import com.shopping.mapper.management.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class PayServiceImp implements  PayService{

    public String goAliPay(String orderId) throws Exception{
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayResource.getGatewayUrl(),
                aliPayResource.getAppId(),
                aliPayResource.getMerchantPrivateKey(),
                "json",
                aliPayResource.getCharset(),
                aliPayResource.getAlipayPublicKey(),
                aliPayResource.getSignType());

        //设置请求参数
        DecimalFormat df = new DecimalFormat("#.00");

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(aliPayResource.getReturnUrl());
        alipayRequest.setNotifyUrl(aliPayResource.getNotifyUrl());
        Example example = new Example(OrderItem.class);
        example.createCriteria().andEqualTo("orderId",orderId);
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        Double total = 0.0;
        for(OrderItem item : orderItems){
            total += item.getPrice()*item.getNum();
        }
        String txnAmt = df.format(total);

        // 商户订单号, 商户网站订单系统中唯一订单号, 必填
        String out_trade_no = orderId;
        // 付款金额, 必填 单位元
        String total_amount = txnAmt;  // 测试用 1分钱
        // 订单名称, 必填
        String subject = "[1]";
        // 商品描述, 可空, 目前先用订单名称
        String body = subject;

        // 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
        String timeout_express = "1h";
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        String alipayForm = "";
        try {
            alipayForm = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alipayForm;
    }


    public String alipay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//       valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params,
                aliPayResource.getAlipayPublicKey(),
                aliPayResource.getCharset(),
                aliPayResource.getSignType()); //调用SDK验证签名

        if(signVerified) {//验证成功
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            // 交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            if (trade_status.equals("TRADE_SUCCESS")){
//                String merchantReturnUrl = paymentOrderService.updateOrderPaid(out_trade_no, CurrencyUtils.getYuan2Fen(total_amount));
//                notifyFoodieShop(out_trade_no,merchantReturnUrl);
//                historyOrderService.transferState(Integer.parseInt(out_trade_no),0,1);
//                payMapper.insertAliPay(Integer.parseInt(out_trade_no),trade_no,total_amount);
                Orders orders = new Orders();
                orders.setId(Integer.parseInt(out_trade_no));orders.setStatus(1);
                ordersMapper.updateByPrimaryKeySelective(orders);
            }
            return "success";
        }else {
            //验证失败
            return "fail";
        }
    }


    public Map alipayCreateOrderRefund(String orderId, String origQryId, String txnAmt) {
        Map map = new LinkedHashMap();

        AlipayClient alipayClient = new DefaultAlipayClient(aliPayResource.getGatewayUrl(),
                aliPayResource.getAppId(), aliPayResource.getMerchantPrivateKey(),
                "json", aliPayResource.getCharset(), aliPayResource.getAlipayPublicKey(), "RSA2");
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

        AlipayTradeRefundModel model= new AlipayTradeRefundModel();
        model.setOutTradeNo(orderId);//订单支付时传入的商户订单号
        model.setTradeNo(origQryId);//支付宝交易号
        model.setRefundAmount(txnAmt);//refund_amount    需要退款的金额，该金额不能大于订单金额,单位为元

        request.setBizModel(model);//请求参数

        AlipayTradeRefundResponse response=null;
        try {
            response = alipayClient.execute(request);
            System.out.println(JSONObject.fromObject(response).toString());
            Map tuiMap= JSONObject.fromObject(response);

        }catch ( AlipayApiException e){
            String massage = "alipay.trade.refund退款接口：订单签名错误";
            System.out.println(massage);
        }
        if(response.isSuccess()){
            System.out.println("调用成功");
            map.put("status", 0);//订单退款  status：0 成功 1:失败
            //log.info("支付宝：支付订单支付结果查询：订单out_trade_no----"+orderId+"---订单退款成功！");
//            Pay pay=payMapper.getPayByOrderId(Integer.parseInt(orderId));
//            pay=payMapper.getPayByOrigQryId(origQryId);//得到订单
//            //修改订单状态为已退款
//            historyOrderMapper.refundOrder(pay.getOrderId());
        } else {
            System.out.println("调用失败");
            map.put("status",1);//订单退款  status：0 成功 1:失败
            //log.info("支付宝：支付订单支付结果查询：订单out_trade_no----"+orderId+"---订单退款失败！");
        }
        return map;
    }

    @Autowired
    AliPayResource aliPayResource;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
}

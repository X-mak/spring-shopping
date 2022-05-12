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
import com.shopping.utils.AcpService;
import com.shopping.utils.SDKConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import net.sf.json.JSONObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PayServiceImp implements  PayService{

    @Value("${natapp}")
    private String natapp;

    private static String merId = "777290058198376";

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

        String notifyUrl = natapp + "payment/backAlipay";
        System.out.println("---<notifyUrl>---"+notifyUrl);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(aliPayResource.getReturnUrl());
        alipayRequest.setNotifyUrl(notifyUrl);
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


    @Override
    public String goUnionPay(String orderId,HttpServletResponse resp) {
        //修改订单状态为待支付
        String orderIdUnion=getUUID();
        String txnAmt = "1.0";

        //跳转至订单平台
        resp.setContentType("text/html; charset=UTF-8");
        txnAmt=(int)(Double.parseDouble(txnAmt)*100)+"";

        Map<String, String> requestData = new HashMap<String, String>();
        /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
        requestData.put("version", "5.0.0");   			  //版本号，全渠道默认值
        requestData.put("encoding", "UTF-8"); 			  //字符集编码，可以使用UTF-8,GBK两种方式
        requestData.put("signMethod", "01"); //签名方法
        requestData.put("txnType", "01");               			  //交易类型 ，01：消费
        requestData.put("txnSubType", "01");            			  //交易子类型， 01：自助消费
        requestData.put("bizType", "000201");           			  //业务类型，B2C网关支付，手机wap支付
        requestData.put("channelType", "07");           			  //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机

        /***商户接入参数***/
        requestData.put("merId", merId);    	          			  //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
        requestData.put("accessType", "0");             			  //接入类型，0：直连商户
        requestData.put("orderId", orderIdUnion);             //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
        requestData.put("txnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));        //订单发送时间，取系统时间，格式为yyyyMMddHHmmss，必须取当前时间，否则会报txnTime无效
        requestData.put("currencyCode", "156");         			  //交易币种（境内商户一般是156 人民币）
        requestData.put("txnAmt", txnAmt);             			      //交易金额，单位分，不要带小数点
        requestData.put("riskRateInfo", "{commodityName=测试商品名称}");

        //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
        //如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
        //异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        requestData.put("frontUrl", "");

        //后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
        //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        //注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码
        //    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
        //    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
        requestData.put("backUrl", "");

        // 订单超时时间。
        // 超过此时间后，除网银交易外，其他交易银联系统会拒绝受理，提示超时。 跳转银行网银交易如果超时后交易成功，会自动退款，大约5个工作日金额返还到持卡人账户。
        // 此时间建议取支付时的北京时间加15分钟。
        // 超过超时时间调查询接口应答origRespCode不是A6或者00的就可以判断为失败。
        requestData.put("payTimeout", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date().getTime() + 15 * 60 * 1000));

        //////////////////////////////////////////////////
        //
        //       报文中特殊用法请查看 special_use_purchase.txt
        //
        //////////////////////////////////////////////////

        /**请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面**/
        Map<String, String> submitFromData = AcpService.sign(requestData,"UTF-8");  //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。

        String requestFrontUrl = "https://gateway.test.95516.com/gateway/api/frontTransReq.do";  //获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
        String html = AcpService.createAutoFormHtml(requestFrontUrl, submitFromData,"UTF-8");   //生成自动跳转的Html表单

        //将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
        return html;
    }

    public static String getUUID(){
        //随机生成一位整数
        int random = (int) (Math.random()*9+1);
        String valueOf = String.valueOf(random);
        //生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        //可能为负数
        if(hashCode<0){
            hashCode = -hashCode;
        }
        String value = valueOf + String.format("%015d", hashCode);
        return value;
    }

    @Autowired
    AliPayResource aliPayResource;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
}

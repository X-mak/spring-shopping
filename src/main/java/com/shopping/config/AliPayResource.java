package com.shopping.config;

import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
@Component
public class AliPayResource {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
//    public static String APP_ID = "2021000117629172";
//
//    // 商户私钥，您的PKCS8格式RSA2私钥
//    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCzlxgJf4PVN1PTXK/p62/WyfHu4NlnLpgwvRzpbA09+5FPR8QgYR6OJOozGnN32YcwQ6LkRN4smkxejwNtCY5S7vSrWA9aJ2b3juMfHToOEM13/j+M5eKowK54A49R2Tr3PPvGx4I0tBkcWGrEkbyUYnwpWQDZTK8lBD/GmoWJQx4UR6a0i+7K+xKbxCcdd91R0AY3JYyRum13nUhM0RfSMjaLLHSk8Hg131orWyuHcQ/B4B70MRBQyY0MNfdrSnd/qOEhjh9mSCBoNc36fXstcxnX6TxeLyoWCrMmpcp2MTx7Ji7lv1SKEpInLqG2IpgxYBFyBQhrAGk70m4tVj+3AgMBAAECggEATGFJjtiV2DvkwfMcui8w7LgM1lz4+aJehfQYdMpIw4DX51ENf/vuQ+Q9Wyi3zVRN4+AQSmdj2rJxIpJOkbRLvMf2dV1Jgj1Z1JYH0Lbcdxj8WjlfC+zFvqHcUhypetUZ+AcOyhcWMJYcS+rq1dlpy+4rAmbhLoonv2WUPkJ/4sVbVghS96PO3/5vmqBTLNPGe7Ugu8/CC5hCKRJG+WSEV9KP6MuAbXtKCngCdwtkgQ4AL29s4nPqdRk8JTxwRUGxQdo9CMpcez0fbVzowh6hpHtvl0zUXiG3nOhUQZSvWXsztoQerVNi4hbpyyFpslE5+wI6AV/dw94Dlh+fXYE1yQKBgQDgTMC0sy6ATqohxpPgdBt/4zc89TmV9t0ieYgqIk+kxHhzDhzutdwwFXCBBKOFM3FSHsRg1nDPgWLLovreZzTHfAXDOyggtXMYEf5wt9zL4WxRccyEhBJDboMpuEqrgA+SzxRh0RqCLWJBlSH2jHu+EdDfmbDZcbUWyF6laB9zOwKBgQDM+LtQwMzFTwPp9lFZ7eVnfmqHZVngLqsE5wZ8ijs6H/eKiDT1DF8J9XxZn63DG7iH8MIByfGxuXZgSid+SYd4RLR1TLEhg07i+D8UA3P3sDOHcsrJQcvN119C4F+AqjBY14EwapB8PAfMXkGQVlNx2WyrhBby9JgCNGFbCrLltQKBgQCAxzBgzvtkyUVezP88AA2F2HNveS/NzQVbdMFbx4VIiVDKZhDwcNNxe+2y9XHY2Rc32dhP7O6qBJQvbGxyYnjoC2nXlFBOrz7cVABswPNwzwT8ocSVVKqTspB11IxyeLz4QhJmssJweRQPqUrtneyxzNLKfhgOT/1zjijg+K4N3wKBgEMPtilrixObEIrBnkgv/3hm8e+uh0NDwYxvEb45c7tGrjTS75ClJYsamD6ycaV+Mle3u6HeMoeVSFekq1s0L3B3+hrOpr1yOqMj2W9w2cZWDeXo9tP2BPop1u8IffaYuAy5JXlzxCYBdgO1avoQZGjBXD6ZjvCaeglundOtom5pAoGBAKVtdxlnfGYtMKzbrq6tmRcR29B2pol1fLRLVNPIc/lMfaGWHOHaFf9yH1PC8WcfCCARvS0nsiUMZBARg1uj/txQpABvyx6yux+1chuzHMW+fgQbKnpn9ni0ewkvFA6TlV7dVf62HJIWpDsTqmcfdMntUUKQy2vYAoMrjl0Zrqcf";
//
//    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
//    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnvecwFXRgdDes5uqtS0VnP1KKhfjzfpy0U+HEFvxJQBlykbt3QQrTqBpZgI3lhbG1hf1oQ9tg+utKfFyKt/n5KPlBWuBqFrljlRx0K8PhUxnahRgWpcI2QtB+f6qN7qcuh1+0AmEvMeOqE/v067fMM61O+I8QpRVqSrUHBhWiVJ45gZ6jFYm/c3s7NNL2Hh7w8KZ7acYxPI6GHsff/xAR5X+FO+BcD3PelXLvOMLUuATrFBeEdqTTzGBymZGOgikZndhegTkMyfrpIBaG+6kzYveajgiDaEtsBmZwmRdM7YCV14NP6VRrTdQKWyzxLYJnINmINS//FlHapLTIdpBiwIDAQAB";

    private String appId = "2021000119682421";//在后台获取（必须配置）

    //私钥直接使用工具的私钥
    private String merchantPrivateKey = "MIIEugIBADANBgkqhkiG9w0BAQEFAASCBKQwggSgAgEAAoIBAQChdlosiFS52LeFCRor3l4kjherWPDWwreq74GL7Pc35vSrXQ3qwLm1zL1+EtPNnYK9M+cKYVSF4Atsob0zE0ZVGyaj0FknVAhnUhrHGZDJQ0EF0exIhZ7oAZgaXVmE+HuqoHljKjSSzYbQn0RJr6SESUL9So6v7QgiOagwq+y6Xu1IZPfCRnrkhVEBKwIenTZIBi5VNp7uDtjCGSfXPP2taKiBACekA2PO7nnTIf3G041dHK77XHdWf62f2S29KMRTY2hWmpktw/ETFGcLapTN35LKGSMyaSO3iACheRW96J6GSuXwBap3FBPqvciipiW+ViDnk/K3gNmrH1riY0sDAgMBAAECggEALyM7Tjb67UrswQUYQ+hmeauWE2si3L/6GQhVPMGQpxxhH8pJ7tgXH5FHXRAoD6ZFbfFCKVCi+XagPzW0471j0iLcFAUWkhnBXlClCq1XDLr6t7VCkyWXb8pPWpsYMG7Ow9CXv7CpqpYgHrXwnMaPdCNjUEk9APVlY4CLrrHyqbHoLsAySLNkLBbfK2YiZjECvSNhWHGI8BNS6Gmyidsp/egmzbC8cciTjaxYB1zXSDujl2BFXAJmYdj1pCAVqOuciewmQeJvsxiyqxaaizZ6Qj9fsosPYIHjA0EnmLzoPoJ1qezBw+mrJ8O5t+BfdWt6IPAZNOYBhzIbs0ugTFuEAQKBgQDTHkq1BOEdCOwn1vXwIf1iWwP1544FBdwZyHwL2aZU6vCq7Rj87mWRo5pAnXj3ezDjRiuW67WcMESyytaxOUxirUX+CERSIUOEiZtEcBTeLlGcIAdL4Hbg4Dj88huDu6DXSkxJ6/YX/SUM+z1/gGCcUOBMpRYOsfZsgvRIn88NAwKBgQDDyaCmMeZyB0iRx5ernKTYpZ/pMbe6mYG96+r35bId97uQQ9tBvi3uxbxgqGi5ljd6uWCKLPUOmwiOriGLVw+YElmfek7RKefXYR2KW6HaT7v2/I+PlQsq8UN/BgVsSPiUXyo+73n++Bh1ZJoT8As7k7lem/YNDjPVD47aArtqAQKBgAuq8uoKOP7HzXVuNe1tk3lqGk/u2jSA3Gc4Y1KsVrr+uQpZTDY+XrKr3gyQJ21WC5+j8yjGIiVdrY5ME4174zLY7LNbdZssZ3Tdvg0MPLsCyFPemUiiQS+wdLXWE5s2NW9wlcLIHbO5s+CByiZPzAcXmS3jOdLaFwpsOmABJQpNAn8leXJGXUa+jKZXQNzG/DSRQyJYgCuqi7dJot5WlKCDG+fKtTJCYyRnj3F/Drz8yMPVuaXAzNoa8ZGxvaKuCPvzH5AH9mEfp+W4U95TnTZdkuVnYPAZi22XypYgugZPs6fgvlZXtX6kTthfn+Hd7xQ3Uw8oDLILzaweG9w73koBAoGAd3M82aPEYjvKxXCX/PUTuqZTt3UJ88N/LHwAAdh4oAOaDGG4AI88WsMIdLZZkOtNkFp5op3lwA0HIZ79nEyLmVG4hENqlwHc5OcrhFf0gnp0HVxdGOApoYpF3mmnBQXvHaB8L3+K5zSzS/noQDvAHc1qcnsphCTe1VmaNb1IZHc=";
    //公钥需要先配置到支付宝平台。从平台获取
    private String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjldLjTd8KGwTxDfxHiIXoZbJVC6nZ6cyxt9Kq84bY4/6wO6HXTzqX7VKfnU3VVH91egijpKsGnpy3/Zpoj8cIPy+5u59q6s7MTnxZCWIUZp9LJH/qO6KvLYv8hSnXuf1tZFzu/dd/6dgl3tajSZpnMDhyWmsj2RQi7Dt3+LgIAqRXsj5ftcJ73Z+CzRp0nfb9jPnbopwp8GPhbnQQLxoX9K7f7t0/EC9pQGtndA1JTSsmU29OW1dNJkx0VeU0CAPQiKoztYnkFs+mwNWgTXBBy3v7lpQbV2CwFdjTpz8c3UiY3I1V4rvVPkYRdDdapQ4EAydMIkVdfEGIkvHYJjCJQIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String notifyUrl= "localhost:8980/payment/backAlipay";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    private String returnUrl= "localhost:8081";

    // 签名方式
    private String signType= "RSA2";

    // 字符编码格式
    private String charset= "utf-8";

    // 支付宝网关，这是沙箱的网关
    private String gatewayUrl= "https://openapi.alipaydev.com/gateway.do";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMerchantPrivateKey() {
        return merchantPrivateKey;
    }

    public void setMerchantPrivateKey(String merchantPrivateKey) {
        this.merchantPrivateKey = merchantPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public AliPayResource(String appId, String merchantPrivateKey, String alipayPublicKey, String notifyUrl, String returnUrl, String signType, String charset, String gatewayUrl) {
        this.appId = appId;
        this.merchantPrivateKey = merchantPrivateKey;
        this.alipayPublicKey = alipayPublicKey;
        this.notifyUrl = notifyUrl;
        this.returnUrl = returnUrl;
        this.signType = signType;
        this.charset = charset;
        this.gatewayUrl = gatewayUrl;
    }

    public AliPayResource() {
        super();
    }


}
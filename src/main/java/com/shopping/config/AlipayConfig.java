package com.shopping.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号,开发时使用沙箱提供的APPID，生产环境改成自己的APPID
//    public static String APP_ID = "2021000117629172";
//
//    // 商户私钥，您的PKCS8格式RSA2私钥
//    public static String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCzlxgJf4PVN1PTXK/p62/WyfHu4NlnLpgwvRzpbA09+5FPR8QgYR6OJOozGnN32YcwQ6LkRN4smkxejwNtCY5S7vSrWA9aJ2b3juMfHToOEM13/j+M5eKowK54A49R2Tr3PPvGx4I0tBkcWGrEkbyUYnwpWQDZTK8lBD/GmoWJQx4UR6a0i+7K+xKbxCcdd91R0AY3JYyRum13nUhM0RfSMjaLLHSk8Hg131orWyuHcQ/B4B70MRBQyY0MNfdrSnd/qOEhjh9mSCBoNc36fXstcxnX6TxeLyoWCrMmpcp2MTx7Ji7lv1SKEpInLqG2IpgxYBFyBQhrAGk70m4tVj+3AgMBAAECggEATGFJjtiV2DvkwfMcui8w7LgM1lz4+aJehfQYdMpIw4DX51ENf/vuQ+Q9Wyi3zVRN4+AQSmdj2rJxIpJOkbRLvMf2dV1Jgj1Z1JYH0Lbcdxj8WjlfC+zFvqHcUhypetUZ+AcOyhcWMJYcS+rq1dlpy+4rAmbhLoonv2WUPkJ/4sVbVghS96PO3/5vmqBTLNPGe7Ugu8/CC5hCKRJG+WSEV9KP6MuAbXtKCngCdwtkgQ4AL29s4nPqdRk8JTxwRUGxQdo9CMpcez0fbVzowh6hpHtvl0zUXiG3nOhUQZSvWXsztoQerVNi4hbpyyFpslE5+wI6AV/dw94Dlh+fXYE1yQKBgQDgTMC0sy6ATqohxpPgdBt/4zc89TmV9t0ieYgqIk+kxHhzDhzutdwwFXCBBKOFM3FSHsRg1nDPgWLLovreZzTHfAXDOyggtXMYEf5wt9zL4WxRccyEhBJDboMpuEqrgA+SzxRh0RqCLWJBlSH2jHu+EdDfmbDZcbUWyF6laB9zOwKBgQDM+LtQwMzFTwPp9lFZ7eVnfmqHZVngLqsE5wZ8ijs6H/eKiDT1DF8J9XxZn63DG7iH8MIByfGxuXZgSid+SYd4RLR1TLEhg07i+D8UA3P3sDOHcsrJQcvN119C4F+AqjBY14EwapB8PAfMXkGQVlNx2WyrhBby9JgCNGFbCrLltQKBgQCAxzBgzvtkyUVezP88AA2F2HNveS/NzQVbdMFbx4VIiVDKZhDwcNNxe+2y9XHY2Rc32dhP7O6qBJQvbGxyYnjoC2nXlFBOrz7cVABswPNwzwT8ocSVVKqTspB11IxyeLz4QhJmssJweRQPqUrtneyxzNLKfhgOT/1zjijg+K4N3wKBgEMPtilrixObEIrBnkgv/3hm8e+uh0NDwYxvEb45c7tGrjTS75ClJYsamD6ycaV+Mle3u6HeMoeVSFekq1s0L3B3+hrOpr1yOqMj2W9w2cZWDeXo9tP2BPop1u8IffaYuAy5JXlzxCYBdgO1avoQZGjBXD6ZjvCaeglundOtom5pAoGBAKVtdxlnfGYtMKzbrq6tmRcR29B2pol1fLRLVNPIc/lMfaGWHOHaFf9yH1PC8WcfCCARvS0nsiUMZBARg1uj/txQpABvyx6yux+1chuzHMW+fgQbKnpn9ni0ewkvFA6TlV7dVf62HJIWpDsTqmcfdMntUUKQy2vYAoMrjl0Zrqcf";
//
//    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
//    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnvecwFXRgdDes5uqtS0VnP1KKhfjzfpy0U+HEFvxJQBlykbt3QQrTqBpZgI3lhbG1hf1oQ9tg+utKfFyKt/n5KPlBWuBqFrljlRx0K8PhUxnahRgWpcI2QtB+f6qN7qcuh1+0AmEvMeOqE/v067fMM61O+I8QpRVqSrUHBhWiVJ45gZ6jFYm/c3s7NNL2Hh7w8KZ7acYxPI6GHsff/xAR5X+FO+BcD3PelXLvOMLUuATrFBeEdqTTzGBymZGOgikZndhegTkMyfrpIBaG+6kzYveajgiDaEtsBmZwmRdM7YCV14NP6VRrTdQKWyzxLYJnINmINS//FlHapLTIdpBiwIDAQAB";

    public static String APP_ID = "2021000119663777";//在后台获取（必须配置）

    //私钥直接使用工具的私钥
    public static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCDk2YhzFLeZJ0iR5msIuW2QAxMVTfLHxMruinB8w/EZ3ByJqS/s9BPdOyf8R01pVobhd+UNu5bmDao+NDDLXUY1r8tqeM323fYeQBRx6a3KB31QivbD029Ukw54L60GaewZGr0nYqfiypjU/Vqzf3WMORaP5t/ZI0ShV51opSRYW7HaxqghxE4SiBWxOCtFb82ERjzg6mAbcgUPwCzJ5TO6YPswJ7ZL0+tii9rTUSv7isJSEDbUQnQiozeJ3l+ECPbt66Xx2uDZfb07EDZq8E+p3ov520yEO4BrwOb7oAxOk32JjrMygOExdJGBp5BWF0RNhwYzqSNcdRlNsuEhR3FAgMBAAECggEAU4Q5PNTT+QQGVZ77JS9VffRzmfQxoQXNhmD5ZnmhFXrO/jYXNvOtEvJVzEQg1U9qvbOhMhQYAh3A/EpLQMhW3nhZC2QdrPPqw4YytLY01WdqdKYW7kptfvGBHu/QPMNYFHf5B+ID/rc4JapCShQMvmt0VvCvBBLQFOSBC8wx34D4GnI0fxIoQ3TeknakKBkcHHwqEFW6OGdc4P52uzXeOn4rpTMiF8xyQgNSeeMZSC3fugVUG08OYLWCazdV2DgHJSbc+fmvM2bfvKNfegd8hHE/Hs1V+VzYpH3hd13xuaV4PkutSIR1IcHe6cBJYJxfNnv5V7JXGJWEpMKLesjM9QKBgQDptgREFKuxeuB/Yl+2jay/rvMNCxFBNzviCvoDfFnP6S4WmVQykfT+pIq5TdjRV9c8qbCgLfuuhfpEnKpGUFOduczJULzPyKAMZGyWgpRtILyAKKVYperqy5PuKAJTZ2wtLNXmoBLmUOHDmM7j0u+cilvIehyhtgJaqOoHUdauXwKBgQCQH8gdVxUYrQrLpiboLASee1fUQrC7SklntG9AErT41CdkUf9GH7dBLGkan1GvhCH7+wRpODKCKuCcC0xqtXnRjgMtsUrP1b1MwSIOaGcM2qNBKxC+vO6rbb86w1GhDug9ek2a7QqgPQX67LUMIeExZec4Zx7e0QgRjYjVhPoeWwKBgHo4fvifmr6BFSBo8McGRj4kHuVGUWq6mO14chEamdlaB4k3/ElJ/UaiBA4uQOIRvvTxhPXvtjwpwpzZFU8aOPA2N3tcZrF/KPvyFLuNZFPBl/KpzvaSJk1N03nroHO/lVn2pZIrSlb6G2bv5dpM+JPz41XvTkPfbFG/HkxfpxafAoGAPDHJ9tckLIY5eA0ROu8HjtmdOT4RamgAeJvXyz8E7thNC41jvPoKI+JUk5XvHAtn1kJk+T1LeypFXWcqp3bcRjW2BiOCR4PR6HjRrIrWX3wBq3o9KCzo0hioQfbJ5SbAwMyQFSDjy0Exejfa9hJAZ81T4S7QqedMI2iluegsPvkCgYANCMXvPPKnkJOE07EwJ0OKxkHBKydAS21ud6Enpv+rur5vyQYpklV2TuvjPgfr50hPvSZN+lzek3SNpkIjPoQeZSr/+X0hJYkUVoi7R4brHeA8yY3QYSlrr1AOA2GksLpH+YM5Xb3LL85u2WC3gw6uXnM6lFPU0RRSPbKyYt8IiA==";
    //公钥需要先配置到支付宝平台。从平台获取
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg5NmIcxS3mSdIkeZrCLltkAMTFU3yx8TK7opwfMPxGdwciakv7PQT3Tsn/EdNaVaG4XflDbuW5g2qPjQwy11GNa/LanjN9t32HkAUcemtygd9UIr2w9NvVJMOeC+tBmnsGRq9J2Kn4sqY1P1as391jDkWj+bf2SNEoVedaKUkWFux2saoIcROEogVsTgrRW/NhEY84OpgG3IFD8AsyeUzumD7MCe2S9PrYova01Er+4rCUhA21EJ0IqM3id5fhAj27eul8drg2X29OxA2avBPqd6L+dtMhDuAa8Dm+6AMTpN9iY6zMoDhMXSRgaeQVhdETYcGM6kjXHUZTbLhIUdxQIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://www.baidu.com";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问(其实就是支付成功后返回的页面)
    public static String return_url = "http://www.baidu.com";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    // 支付宝网关
    public static String log_path = "D:\\Log\\";

    /**
     * 商户订单号，商户网站订单系统中唯一订单号，必填
     */
    public static String outTradeNo = "5110";

    /**
     * 付款金额，必填
     */
    public static String totalAmount = "666";

    /**
     * 订单名称，必填
     */
    public static String subject ="下饭操作";

    /**
     * 商品描述，可空
     */
    public static String body ="这波能五杀";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
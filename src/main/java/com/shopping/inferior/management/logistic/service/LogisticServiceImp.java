package com.shopping.inferior.management.logistic.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shopping.utils.LogisticUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class LogisticServiceImp implements LogisticService{

    @Value("${logistic.logisticApi}")
    private String logisticApi;
    @Value("${logistic.EBusinessID}")
    private String EBusinessID;
    @Value("${logistic.ApiKey}")
    private String ApiKey;

    public String getLogisticInfo(String orderId,Integer status){
        String shipperCode = "SF";
        Map<String, String> map = new HashMap<>(4);
        Integer logisticNum = 1234560 + status;
        String logisticCode = logisticNum+"";
        map.put("OrderCode", orderId == null ? "" : orderId);
        map.put("ShipperCode", shipperCode);
        map.put("LogisticCode", logisticCode);
        String ReqData = JSONObject.toJSON(map).toString();
        Map<String, String> params = new HashMap<>(8);
        try {
            params.put("RequestData", URLEncoder.encode(ReqData, "UTF-8"));
            params.put("EBusinessID", EBusinessID);
            params.put("RequestType", "1002");
            String dataSign = LogisticUtil.encrypt(ReqData, ApiKey, "UTF-8");
            params.put("DataSign", URLEncoder.encode(dataSign, "UTF-8"));
            params.put("DataType", "2");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String result = LogisticUtil.sendPost(logisticApi, params);
        return result;
    }

}

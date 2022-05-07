package com.shopping.inferior.management.logistic.controller;

import com.alibaba.fastjson.JSON;
import com.shopping.common.Result;
import com.shopping.inferior.management.logistic.service.LogisticService;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logistic")
public class LogisticController {
    /**
     * 查询物流进程
     * @param orderId 订单编号
     * @param status 物流中转次数
     */
    @ApiDoc(result = Result.class)
    @GetMapping("")
    public Result<Object> getLogisticInfo(@RequestParam String orderId,
                                          @RequestParam Integer status){
        String logisticInfo = logisticService.getLogisticInfo(orderId, status);
        Object parse = JSON.parse(logisticInfo);
        return Result.success(parse,"ok");
    }

    @Autowired
    LogisticService logisticService;
}

package com.shopping.inferior.goods.controller;

import com.github.pagehelper.PageInfo;
import com.shopping.common.Result;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.goods.GoodsInfo;
import com.shopping.inferior.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @PostMapping
    public Result<?> addGoods(@RequestBody GoodsInfo goodsInfo){
        int res = goodsService.addGoods(goodsInfo);
        if(res == -1)return Result.error("上架失败");
        return Result.success("上架成功");
    }

    @GetMapping("/list/{pageNum}")
    public Result<?> getSelectedGoods(@RequestParam String keyword,@RequestParam String classId,
                                      @RequestParam Integer pageSize,@PathVariable Integer pageNum,
                                      @RequestParam String order,@RequestParam String shopId){
        PageInfo<Goods> goodsBySearch = goodsService.getGoodsBySearch(pageNum, pageSize, keyword, order, classId, shopId);
        if(goodsBySearch.getTotal() == 0)return Result.error("查询结果为空!");
        return Result.success(goodsBySearch.getList(),goodsBySearch.getTotal()+"");
    }

    @Autowired
    GoodsService goodsService;
}

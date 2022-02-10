package com.shopping.inferior.goods.controller;

import com.github.pagehelper.PageInfo;
import com.shopping.common.Result;
import com.shopping.entity.goods.*;
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
    public Result<?> getSelectedGoods(@RequestParam(required = false,defaultValue = "%") String keyword,
                                      @RequestParam(required = false,defaultValue = "%") String classId,
                                      @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                      @PathVariable Integer pageNum,
                                      @RequestParam(required = false,defaultValue = "date") String order,
                                      @RequestParam(required = false,defaultValue = "%") String shopId){
        PageInfo<Goods> goodsBySearch = goodsService.getGoodsBySearch(pageNum, pageSize, keyword, order, classId, shopId);
        if(goodsBySearch.getTotal() == 0)return Result.error("查询结果为空!");
        return Result.success(goodsBySearch.getList(),goodsBySearch.getTotal()+"");
    }

    @GetMapping("/{id}")
    public Result<?> getGoodsInfo(@PathVariable Integer id){
        Goods goods = goodsService.getGoods(id);
        if(goods == null)return Result.error("不存在该商品");
        return Result.success(goods,"查询成功!");
    }

    @DeleteMapping("/{goodsId}")
    public Result<?> deleteGoods(@PathVariable Integer goodsId){
        int res = goodsService.deleteGoods(goodsId);
        if(res == -1)return Result.error("删除失败");
        return Result.success("删除成功");
    }

    @PutMapping("/info")
    public Result<?> changeGoodsInfo(@RequestBody Goods goods){
        int res = goodsService.changeGoodsBasicInfo(goods);
        if(res == -1)return Result.error("更新失败");
        return Result.success("更新成功");
    }

    @PutMapping("/price")
    public Result<?> changeGoodsPrice(@RequestBody Price price){
        int res = goodsService.changePrice(price);
        if(res == -1)return Result.error("更新失败");
        return Result.success("更新成功");
    }

    @PutMapping("/stock")
    public Result<?> changeGoodsStock(@RequestBody Stock stock){
        int res = goodsService.changeStock(stock);
        if(res == -1)return Result.error("更新失败");
        return Result.success("更新成功");
    }

    @PutMapping("/class")
    public Result<?> changeGoodsClass(@RequestBody GoodsClass goodsClass){
        int res = goodsService.changeClass(goodsClass);
        if(res == -1)return Result.error("更新失败");
        return Result.success("更新成功");
    }

    @GetMapping("/class")
    public Result<?> getClass(@RequestParam String classId){
        List<Classes> classes = goodsService.getClasses(classId);
        if(classes.size()!=0)return Result.success(classes,"查询成功!");
        return Result.error("查询失败!");
    }

    @PostMapping("/pictures")
    public Result<?> addPictures(@RequestParam Integer goodsId,@RequestBody List<String> pictures){
        int res = goodsService.addPictures(goodsId, pictures);
        if(res == -1)return Result.error("添加失败");
        return Result.success("添加成功");
    }


    @Autowired
    GoodsService goodsService;
}

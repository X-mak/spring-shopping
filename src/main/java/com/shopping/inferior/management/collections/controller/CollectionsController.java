package com.shopping.inferior.management.collections.controller;

import com.github.pagehelper.PageInfo;
import com.shopping.common.Result;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.management.Collections;
import com.shopping.inferior.management.collections.service.CollectionsService;
import com.shopping.utils.Authority;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collections")
public class CollectionsController {

    /**
     * 收藏商品
     * @param collectionsList   收藏信息列表
     */
    @ApiDoc(result = Result.class)
    @PostMapping("")
    public Result<?> collectGoods(@RequestBody List<Collections> collectionsList){
        int res = collectionsService.collectMany(collectionsList);
        if(res == 1)return Result.success("收藏成功!");
        else return Result.error("收藏失败");
    }

    /**
     * 取消收藏
     * @param id    收藏编号
     */
    @ApiDoc(result = Result.class)
    @DeleteMapping("")
    public Result<?> cancelCollect(@RequestParam Integer id){
        int res = collectionsService.cancelCollect(id);
        if(res == 1)return Result.success("取消成功!");
        else return Result.error("取消失败!");
    }

    /**
     * 获取个人收藏列表
     * @param pageNum   页码
     * @param pageSize  单页大小
     * @return  Result<List<Goods>>
     */
    @ApiDoc
    @GetMapping("/list")
    public Result<List<Goods>> getMyCollections(@RequestParam Integer pageNum,
                                      @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        PageInfo<Goods> selectedCollections = collectionsService.getSelectedCollections(pageNum, pageSize);
        if(selectedCollections.getSize() == 0)return Result.error("暂无数据!");
        return Result.success(selectedCollections.getList(),selectedCollections.getTotal()+"");
    }


    @Autowired
    CollectionsService collectionsService;
    @Autowired
    Authority authority;
}

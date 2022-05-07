package com.shopping.inferior.management.collections.controller;

import com.github.pagehelper.PageInfo;
import com.shopping.common.Result;
import com.shopping.entity.management.CollectedItem;
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
    public Result<?> collectGoods(@RequestBody List<CollectedItem> collectionsList){
        if(!authority.hasRights("buyer"))return Result.error("no way");
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
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = collectionsService.cancelCollect(id);
        if(res == 1)return Result.success("取消成功!");
        else return Result.error("取消失败!");
    }

    /**
     * 获取个人收藏列表
     * @param pageNum   页码
     * @param pageSize  单页大小
     * @return  Result<List<CollectedItem>>
     */
    @ApiDoc
    @GetMapping("/list/{pageNum}")
    public Result<List<CollectedItem>> getMyCollections(@PathVariable Integer pageNum,
                                      @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        PageInfo<CollectedItem> selectedCollections = collectionsService.getSelectedCollections(pageNum, pageSize);
        if(selectedCollections.getSize() == 0)return Result.error("暂无数据!");
        return Result.success(selectedCollections.getList(),selectedCollections.getTotal()+"");
    }

    /**
     * 查看收藏权限
     * @param goodsId 商品编号
     */
    @ApiDoc
    @GetMapping("")
    public Result<?> getOwnership(@RequestParam Integer goodsId){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int collected = collectionsService.isCollected(goodsId);
        if(collected == -1)return Result.error("error");
        else return Result.success(collected,"");
    }

    @Autowired
    CollectionsService collectionsService;
    @Autowired
    Authority authority;
}

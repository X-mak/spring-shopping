package com.shopping.inferior.goods.controller;

import com.github.pagehelper.PageInfo;
import com.shopping.common.Result;
import com.shopping.entity.goods.*;
import com.shopping.inferior.goods.service.GoodsService;
import com.shopping.utils.Authority;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    /**
     * 新增商品
     * @param goodsInfo 商品信息
     */
    @ApiDoc(result = Result.class)
    @PostMapping("")
    public Result<?> addGoods(@RequestBody GoodsInfo goodsInfo){
        if(!authority.hasRights("seller"))return Result.error("no way");
        int res = goodsService.addGoods(goodsInfo);
        if(res == -1)return Result.error("上架失败");
        return Result.success("上架成功");
    }

    /**
     * 搜索商品
     * @param keyword   搜索关键字
     * @param pageSize  单页数量
     * @param pageNum   页码
     * @param order     排序方式，date/sales
     * @param shopId    商铺编号
     * @param status    商品状态
     * @return Result<List<Goods>>
     *
     */
    @ApiDoc
    @GetMapping("/list/{pageNum}")
    public Result<List<Goods>> getSelectedGoods(@RequestParam(required = false,defaultValue = "%") String keyword,
                                      @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                      @PathVariable Integer pageNum,
                                      @RequestParam(required = false,defaultValue = "date") String order,
                                      @RequestParam(required = false,defaultValue = "%") String shopId,
                                      @RequestParam(required = false,defaultValue = "1") String status){
        PageInfo<Goods> goodsBySearch = goodsService.getGoodsBySearch(pageNum, pageSize, keyword, order, shopId,status);
        if(goodsBySearch.getTotal() == 0)return Result.error("查询结果为空!");
        return Result.success(goodsBySearch.getList(),goodsBySearch.getTotal()+"");
    }

    /**
     * 获取商品信息
     * @param id    商品编号
     * @return  Result<Goods>
     */
    @ApiDoc
    @GetMapping("/{id}")
    public Result<Goods> getGoodsInfo(@PathVariable Integer id){
        Goods goods = goodsService.getGoods(id);
        if(goods == null)return Result.error("不存在该商品");
        return Result.success(goods,"查询成功!");
    }


    /**
     * 修改商品基本信息
     * @param goods     商品信息
     */
    @ApiDoc(result = Result.class)
    @PutMapping("/info")
    public Result<?> changeGoodsInfo(@RequestBody Goods goods){
        if(!authority.hasRights("seller"))return Result.error("no way");
        int res = goodsService.changeGoodsBasicInfo(goods);
        if(res == -1)return Result.error("更新失败");
        return Result.success("更新成功");
    }

    /**
     * 修改商品价格
     * @param price 价格信息
     */
    @ApiDoc(result = Result.class)
    @PutMapping("/price")
    public Result<?> changeGoodsPrice(@RequestBody Price price){
        if(!authority.hasRights("seller"))return Result.error("no way");
        int res = goodsService.changePrice(price);
        if(res == -1)return Result.error("更新失败");
        return Result.success("更新成功");
    }

    /**
     * 修改商品库存
     * @param stock 库存信息
     */
    @ApiDoc(result = Result.class)
    @PutMapping("/stock")
    public Result<?> changeGoodsStock(@RequestBody Stock stock){
        if(!authority.hasRights("seller"))return Result.error("no way");
        int res = goodsService.changeStock(stock);
        if(res == -1)return Result.error("更新失败");
        return Result.success("更新成功");
    }

    /**
     * 修改商品类别
     * @param goodsClass    类别信息
     */
    @ApiDoc(result = Result.class)
    @PutMapping("/class")
    public Result<?> changeGoodsClass(@RequestBody GoodsClass goodsClass){
        if(!authority.hasRights("seller"))return Result.error("no way");
        int res = goodsService.changeClass(goodsClass);
        if(res == -1)return Result.error("更新失败");
        return Result.success("更新成功");
    }

    /**
     * 获取商品类别列表
     * @param classId 类别编号
     * @return Result<List<Classes>>
     */
    @ApiDoc
    @GetMapping("/class")
    public Result<List<Classes>> getClass(@RequestParam String classId){
        List<Classes> classes = goodsService.getClasses(classId);
        if(classes.size()!=0)return Result.success(classes,"查询成功!");
        return Result.error("查询失败!");
    }

    /**
     * 新增图片
     * @param goodsId   商品编号
     * @param pictures  图片列表信息
     */
    @ApiDoc(result = Result.class)
    @PostMapping("/pictures")
    public Result<?> addPictures(@RequestParam Integer goodsId,@RequestBody List<String> pictures){
        if(!authority.hasRights("seller"))return Result.error("no way");
        int res = goodsService.addPictures(goodsId, pictures);
        if(res == -1)return Result.error("添加失败");
        return Result.success("添加成功");
    }


    @Autowired
    GoodsService goodsService;
    @Autowired
    Authority authority;
}

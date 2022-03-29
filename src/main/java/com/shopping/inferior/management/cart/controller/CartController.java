package com.shopping.inferior.management.cart.controller;

import com.github.pagehelper.PageInfo;
import com.shopping.common.Result;
import com.shopping.entity.goods.Goods;
import com.shopping.inferior.management.cart.service.CartService;
import com.shopping.utils.Authority;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    /**
     * 购物车添加商品
     * @param goodsId   商品编号
     */
    @ApiDoc(result = Result.class)
    @PostMapping("")
    public Result<?> addCartGoods(@RequestParam Integer goodsId,@RequestParam Integer num){
        if(authority.hasRights("buyer"))
            return Result.error("no way");
        int res = cartService.addCartGoods(goodsId,num);
        if(res == 1)return Result.success("添加成功!");
        else return Result.error("添加失败!");
    }

    /**
     * 购物车删除商品
     * @param id    购物车编号
     */
    @ApiDoc(result = Result.class)
    @DeleteMapping("")
    public Result<?> deleteCartGoods(@RequestParam Integer id){

        int res = cartService.deleteCartGoods(id);
        if(res == 1)return Result.success("删除成功!");
        else return Result.error("删除失败!");
    }

    /**
     * 更新购物车商品数量
     * @param id    购物车编号
     * @param num   修改后数量
     */
    @ApiDoc(result = Result.class)
    @PutMapping("")
    public Result<?> updateCartGoods(@RequestParam Integer id,
                                     @RequestParam Integer num){
        int res = cartService.updateCartGoods(id, num);
        if(res == 1)return Result.success("更新成功!");
        else return Result.error("更新失败!");
    }

    /**
     * 获取购物车列表
     * @param pageNum   页码
     * @param pageSize  单页大小
     * @return  Result<List<Goods>>
     */
    @ApiDoc
    @GetMapping("/list")
    public Result<List<Goods>> getCartGoods(@RequestParam Integer pageNum,
                                     @RequestParam(required = false,defaultValue = "10") Integer pageSize
                                  ){
        PageInfo<Goods> cartGoods = cartService.getCartGoods(pageNum, pageSize);
        if (cartGoods.getSize()==0)return Result.error("暂无数据!");
        return Result.success(cartGoods.getList(),cartGoods.getTotal()+"");
    }


    @Autowired
    CartService cartService;
    @Autowired
    Authority authority;

}

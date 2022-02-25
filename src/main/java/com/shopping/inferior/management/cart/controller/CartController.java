package com.shopping.inferior.management.cart.controller;

import com.github.pagehelper.PageInfo;
import com.shopping.common.Result;
import com.shopping.entity.goods.Goods;
import com.shopping.inferior.management.cart.service.CartService;
import com.shopping.utils.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @PostMapping
    public Result<?> addCartGoods(@RequestParam Integer goodsId){

        int res = cartService.addCartGoods(goodsId);
        if(res == 1)return Result.success("添加成功!");
        else return Result.error("添加失败!");
    }

    @DeleteMapping
    public Result<?> deleteCartGoods(@RequestParam Integer id){

        int res = cartService.deleteCartGoods(id);
        if(res == 1)return Result.success("删除成功!");
        else return Result.error("删除失败!");
    }

    @PutMapping
    public Result<?> updateCartGoods(@RequestParam Integer id,
                                     @RequestParam Integer num){
        int res = cartService.updateCartGoods(id, num);
        if(res == 1)return Result.success("更新成功!");
        else return Result.error("更新失败!");
    }

    @GetMapping("/list")
    public Result<?> getCartGoods(@RequestParam Integer pageNum,
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

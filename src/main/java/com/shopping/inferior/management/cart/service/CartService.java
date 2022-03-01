package com.shopping.inferior.management.cart.service;

import com.github.pagehelper.PageInfo;
import com.shopping.entity.goods.Goods;

public interface CartService {

    int addCartGoods(Integer goodsId);

    int deleteCartGoods(Integer id);

    int updateCartGoods(Integer id,Integer num);

    PageInfo<Goods> getCartGoods(Integer pageNum,Integer pageSize);
}

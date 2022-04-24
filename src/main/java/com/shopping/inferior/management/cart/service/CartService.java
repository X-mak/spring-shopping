package com.shopping.inferior.management.cart.service;

import com.github.pagehelper.PageInfo;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.management.CartItem;

public interface CartService {

    int addCartGoods(Integer goodsId,Integer num);

    int deleteCartGoods(Integer id);

    int updateCartGoods(Integer id,Integer num);

    PageInfo<CartItem> getCartGoods(Integer pageNum, Integer pageSize);
}

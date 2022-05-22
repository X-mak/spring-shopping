package com.shopping.inferior.management.cart.service;

import com.github.pagehelper.PageInfo;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.management.CartItem;

import java.util.List;

public interface CartService {

    int addCartGoods(Integer goodsId,Integer num);

    int deleteMultiCartGoods(List<Integer> carts);

    int updateCartGoods(Integer id,Integer num);

    PageInfo<CartItem> getCartGoods(Integer pageNum, Integer pageSize);
}

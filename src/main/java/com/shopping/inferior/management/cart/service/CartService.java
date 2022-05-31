package com.shopping.inferior.management.cart.service;

import com.github.pagehelper.PageInfo;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.management.CartItem;
import com.shopping.entity.management.OrderItem;

import java.util.List;

public interface CartService {

    int addCartGoods(Integer goodsId,Integer num);

    int deleteMultiCartGoods(List<OrderItem> ordersList);

    int deleteCartGoods(Integer id);

    int updateCartGoods(Integer id,Integer num);

    PageInfo<CartItem> getCartGoods(Integer pageNum, Integer pageSize);
}

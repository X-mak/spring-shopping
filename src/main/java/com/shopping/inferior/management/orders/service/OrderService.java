package com.shopping.inferior.management.orders.service;

import com.github.pagehelper.PageInfo;
import com.shopping.entity.management.Orders;

import java.util.List;

public interface OrderService {

    int addOrders(Orders orders);

    int deleteOrders(Integer id,Integer goodsId);

    int changeOrders(Orders orders);

    PageInfo<Orders> getOrdersListByShop(Integer pageNum, Integer pageSize, Integer shopId, Integer status);

    PageInfo<Orders> getOrdersListByUser(Integer pageNum, Integer pageSize, Integer userId, Integer status);

    Orders getOrdersById(Integer id);

}

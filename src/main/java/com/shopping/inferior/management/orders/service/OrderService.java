package com.shopping.inferior.management.orders.service;

import com.shopping.entity.management.Orders;

import java.util.List;

public interface OrderService {

    int addOrders(Orders orders);

    int deleteOrders(Integer id,Integer goodsId);

    int changeOrders(Orders orders);

    List<Orders> getOrdersList(Integer pageNum,Integer pageSize,Integer shopId,Integer status);

    Orders getOrdersById(Integer id);

}

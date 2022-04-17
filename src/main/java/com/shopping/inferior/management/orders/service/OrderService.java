package com.shopping.inferior.management.orders.service;

import com.github.pagehelper.PageInfo;
import com.shopping.entity.management.OrderItem;
import com.shopping.entity.management.Orders;

import java.util.List;

public interface OrderService {

    int addOrders(OrderItem orderItem);

    int deleteOrders(Integer id,Integer status);

    int changeOrders(Orders orders);

    PageInfo<Orders> getOrdersListByShop(Integer pageNum, Integer pageSize, Integer shopId, String status);

    PageInfo<Orders> getOrdersListByUser(Integer pageNum, Integer pageSize, Integer userId, String status);

    Orders getOrdersById(Integer id);

    int addOrders(List<OrderItem> ordersList);
}

package com.shopping.inferior.management.orders.controller;

import com.github.pagehelper.PageInfo;
import com.shopping.common.Result;
import com.shopping.entity.management.Orders;
import com.shopping.inferior.management.orders.service.OrderService;
import com.shopping.utils.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping
    public Result<?> addOrders(@RequestBody Orders orders){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = orderService.addOrders(orders);
        if(res == -1)return Result.error("未知错误!");
        else return Result.success("下单成功!");
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteOrders(@PathVariable Integer id,@RequestParam Integer goodsId){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = -1;
        if(authority.hasRights("seller"))res = orderService.deleteOrders(id, goodsId,3);
        else res = orderService.deleteOrders(id, goodsId,1);
        if(res == -1)return Result.error("未知错误!");
        else return Result.success("删除成功!");
    }

    @PutMapping
    public Result<?> changeOrders(@RequestBody Orders orders){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = orderService.changeOrders(orders);
        if(res == -1)return Result.error("未知错误!");
        else return Result.success("更新成功!");
    }

    @GetMapping("/userList/{pageNum}")
    public Result<?> getOrdersListByUser(@PathVariable Integer pageNum,
                                         @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                         @RequestParam Integer userId,
                                         @RequestParam(required = false,defaultValue = "%") Integer status){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        PageInfo<Orders> ordersListByUser = orderService.getOrdersListByUser(pageNum, pageSize, userId, status);
        return Result.success(ordersListByUser.getList(),ordersListByUser.getTotal()+"");
    }

    @GetMapping("/{id}")
    public Result<?> getOneOrder(@PathVariable Integer id){
        Orders ordersById = orderService.getOrdersById(id);
        if(ordersById == null)return Result.error("不存在此订单");
        else return Result.success(ordersById,"查询成功!");
    }

    @Autowired
    OrderService orderService;
    @Autowired
    Authority authority;
}

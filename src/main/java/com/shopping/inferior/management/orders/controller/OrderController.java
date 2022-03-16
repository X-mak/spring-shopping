package com.shopping.inferior.management.orders.controller;

import com.github.pagehelper.PageInfo;
import com.shopping.common.Result;
import com.shopping.entity.management.Orders;
import com.shopping.inferior.management.orders.service.OrderService;
import com.shopping.utils.Authority;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    /**
     * 新增订单
     * @param orders    订单信息
     */
    @ApiDoc(result = Result.class)
    @PostMapping("")
    public Result<?> addOrders(@RequestBody Orders orders){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = orderService.addOrders(orders);
        if(res == -1)return Result.error("未知错误!");
        else return Result.success("下单成功!");
    }

    /**
     * 批量新增订单
     * @param ordersList    订单信息列表
     */
    @ApiDoc(result = Result.class)
    @PostMapping("/list")
    public Result<?> addOrdersList(@RequestBody List<Orders> ordersList){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = orderService.addOrders(ordersList);
        if(res == -1)return Result.error("未知错误!");
        else return Result.success("下单成功!");
    }

    /**
     * 删除订单
     * @param id    订单编号
     * @param goodsId   商品编号
     */
    @ApiDoc(result = Result.class)
    @DeleteMapping("/{id}")
    public Result<?> deleteOrders(@PathVariable Integer id,@RequestParam Integer goodsId){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = -1;
        if(authority.hasRights("seller"))res = orderService.deleteOrders(id, goodsId,3);
        else res = orderService.deleteOrders(id, goodsId,1);
        if(res == -1)return Result.error("删除失败!");
        else return Result.success("删除成功!");
    }

    /**
     * 更新订单状态
     * @param orders    订单信息
     */
    @ApiDoc(result = Result.class)
    @PutMapping("")
    public Result<?> changeOrders(@RequestBody Orders orders){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = orderService.changeOrders(orders);
        if(res == -1)return Result.error("未知错误!");
        else return Result.success("更新成功!");
    }

    /**
     * 获取订单列表
     * @param pageNum   页码
     * @param pageSize  单页大小
     * @param userId    用户编号
     * @param status    订单状态
     * @param shopId    卖家编号
     * @return Result<List<Orders>>
     */
    @ApiDoc
    @GetMapping("/list/{pageNum}")
    public Result<List<Orders>> getOrdersListByUser(@PathVariable Integer pageNum,
                                            @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                            @RequestParam String userId,
                                            @RequestParam(required = false,defaultValue = "%") Integer status,
                                            @RequestParam(required = false) String shopId      ){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        PageInfo<Orders> ordersListByUser = null;
        if(userId!=null && shopId == null)
            ordersListByUser = orderService.getOrdersListByUser(pageNum, pageSize, Integer.parseInt(userId), status);
        else if(userId == null && shopId != null)
            ordersListByUser = orderService.getOrdersListByShop(pageNum,pageSize,Integer.parseInt(shopId),status);
        else return Result.error("错误的请求格式");

        return Result.success(ordersListByUser.getList(),ordersListByUser.getTotal()+"");
    }

    /**
     * 获取订单
     * @param id    订单编号
     * @return  Result<Orders>
     */
    @ApiDoc
    @GetMapping("/{id}")
    public Result<Orders> getOneOrder(@PathVariable Integer id){
        Orders ordersById = orderService.getOrdersById(id);
        if(ordersById == null)return Result.error("不存在此订单");
        else return Result.success(ordersById,"查询成功!");
    }

    @Autowired
    OrderService orderService;
    @Autowired
    Authority authority;
}

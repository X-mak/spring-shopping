package com.shopping.inferior.management.orders.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.management.Orders;
import com.shopping.mapper.goods.GoodsMapper;
import com.shopping.mapper.management.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService{

    public int addOrders(Orders orders){
        //是否有库存
        Goods goods = goodsMapper.selectByPrimaryKey(orders.getGoodsId());
        if(goods.getStock() < orders.getOrderNum())return 0;
        try{
            orders.setDate(DateUtil.now());
            ordersMapper.insertSelective(orders);
            goods.setStock(goods.getStock()-orders.getOrderNum());
            if (goods.getStock() == 0)goods.setGoodsStatus(0);
            goodsMapper.updateByPrimaryKeySelective(goods);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int deleteOrders(Integer id,Integer goodsId,Integer status){
        try{
            Orders orders = ordersMapper.selectByPrimaryKey(id);
            if(orders.getOrderStatus() > status)return 0;
            ordersMapper.deleteByPrimaryKey(id);
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            if(goods.getGoodsStatus() == 0)goods.setGoodsStatus(1);
            goods.setStock(goods.getStock()+orders.getOrderNum());
            goodsMapper.updateByPrimaryKeySelective(goods);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int changeOrders(Orders orders){
        try{
            ordersMapper.updateByPrimaryKeySelective(orders);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public PageInfo<Orders> getOrdersListByShop(Integer pageNum, Integer pageSize, Integer shopId, Integer status){
        PageHelper.startPage(pageNum,pageSize,true);
        List<Orders> orders = ordersMapper.queryOrdersByShop(shopId, status);
        return new PageInfo<>(orders);
    }

    public PageInfo<Orders> getOrdersListByUser(Integer pageNum, Integer pageSize, Integer userId, Integer status){
        PageHelper.startPage(pageNum,pageSize,true);
        List<Orders> orders = ordersMapper.queryOrdersByUser(userId, status);
        return new PageInfo<>(orders);
    }

    public Orders getOrdersById(Integer id){
        return ordersMapper.queryOrdersById(id);
    }

    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    GoodsMapper goodsMapper;
}

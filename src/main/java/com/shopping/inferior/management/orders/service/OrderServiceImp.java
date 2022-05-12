package com.shopping.inferior.management.orders.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.data.GoodsEx;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.management.OrderItem;
import com.shopping.entity.management.Orders;
import com.shopping.mapper.data.GoodsExMapper;
import com.shopping.mapper.goods.GoodsMapper;
import com.shopping.mapper.management.OrderItemMapper;
import com.shopping.mapper.management.OrdersMapper;
import com.shopping.utils.AccessControlUtil;
import com.shopping.utils.TokenUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Service
public class OrderServiceImp implements OrderService{

    public int addOrders(OrderItem orderItem){
        //是否有库存
        Goods goods = goodsMapper.selectByPrimaryKey(orderItem.getGoodsId());
        if(goods.getStock() < orderItem.getNum())return 0;
        Orders orders = new Orders();
        try{
            Integer userId = TokenUtils.getLoginUser().getId();
            orders = new Orders(userId,0);
            ordersMapper.insertSelective(orders);
            goods.setStock(goods.getStock()-orderItem.getNum());
            orderItem.setOrderId(orders.getId());
            orderItemMapper.insertSelective(orderItem);
            if (goods.getStock() == 0)goods.setGoodsStatus(0);
            goodsMapper.updateByPrimaryKeySelective(goods);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return orders.getId();
    }

    public int deleteOrders(Integer id,Integer status){
        try{
            //检验权限
            Orders orders = ordersMapper.selectByPrimaryKey(id);
            Integer userId = TokenUtils.getLoginUser().getId();
            if(orders.getUserId() != userId)return -1;
            if(orders.getStatus() > status)return 0;

            Example example = new Example(OrderItem.class);
            example.createCriteria().andEqualTo("orderId",id);
            List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
            //遍历大订单
            for(OrderItem item : orderItems){
                //返回库存
                Goods goods = goodsMapper.selectByPrimaryKey(item.getGoodsId());
                if(goods.getGoodsStatus() == 0)goods.setGoodsStatus(1);
                goods.setStock(goods.getStock()+item.getNum());
                goodsMapper.updateByPrimaryKeySelective(goods);
                goodsExMapper.insertSelective(new GoodsEx(item.getGoodsId(), 1,goods.getStock()+item.getNum()+"",0));
                //删除单项订单
                orderItemMapper.deleteByPrimaryKey(item);
            }
            //删除大订单
            ordersMapper.deleteByPrimaryKey(orders);
//            Orders orders = ordersMapper.selectByPrimaryKey(id);
//            if(orders.getStatus() > status)return 0;
//            ordersMapper.deleteByPrimaryKey(id);
//            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
//            if(goods.getGoodsStatus() == 0)goods.setGoodsStatus(1);
//            goods.setStock(goods.getStock()+orders.getOrderNum());
//            goodsMapper.updateByPrimaryKeySelective(goods);
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

    public PageInfo<Orders> getOrdersListByShop(Integer pageNum, Integer pageSize, Integer shopId, String status){
        PageHelper.startPage(pageNum,pageSize,true);
        List<Orders> orders = ordersMapper.queryOrdersByShop(shopId, status);
        return new PageInfo<>(orders);
    }

    public PageInfo<Orders> getOrdersListByUser(Integer pageNum, Integer pageSize, Integer userId, String status){
        PageHelper.startPage(pageNum,pageSize,true);
        List<Orders> orders = ordersMapper.queryOrdersByUser(userId, status);
        return new PageInfo<>(orders);
    }

    public Orders getOrdersById(Integer id){
        return ordersMapper.queryOrdersById(id);
    }

    public int addOrders(List<OrderItem> ordersList){

        try{
//            for(Orders orders:ordersList){
//                Goods goods = goodsMapper.selectByPrimaryKey(orders.getGoodsId());
//                if(goods.getStock() < orders.getOrderNum())return 0;
//                orders.setDate(DateUtil.now());
//                ordersMapper.insertSelective(orders);
//                goods.setStock(goods.getStock()-orders.getOrderNum());
//                if (goods.getStock() == 0)goods.setGoodsStatus(0);
//                goodsMapper.updateByPrimaryKeySelective(goods);
//           }
            Integer userId = TokenUtils.getLoginUser().getId();
            Map<Integer,List<OrderItem>> hashMap = new HashMap<Integer,List<OrderItem>>();
            //遍历小订单
            for (OrderItem item : ordersList){
                //判断发货地址是否相同
                if(hashMap.containsKey(item.getShopId())){
                    //同一个店铺则链表增加
                    hashMap.get(item.getShopId()).add(item);
                }else{
                    //不同店铺则新建
                    ArrayList<OrderItem> orderItems = new ArrayList<>();
                    orderItems.add(item);
                    hashMap.put(item.getShopId(),orderItems);
                }
            }

            //遍历哈希表
            for (List<OrderItem> next : hashMap.values()) {
                //生成新的大订单
                Orders orders = new Orders(userId, 0);
                ordersMapper.insertSelective(orders);
                for(OrderItem item : next){
                    //生成订单项
                    item.setOrderId(orders.getId());
                    Goods goods = goodsMapper.selectByPrimaryKey(item.getGoodsId());
                    goods.setStock(goods.getStock()-item.getNum());
                    goodsMapper.updateByPrimaryKeySelective(goods);
                    goodsExMapper.updateByPrimaryKeySelective(new GoodsEx(item.getGoodsId(), 1,goods.getStock()-item.getNum()+"",0));
                    orderItemMapper.insertSelective(item);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    GoodsExMapper goodsExMapper;
    @Autowired
    AccessControlUtil accessControlUtil;
}

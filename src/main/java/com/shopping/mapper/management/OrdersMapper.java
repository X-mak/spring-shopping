package com.shopping.mapper.management;

import cn.hutool.db.sql.Order;
import com.shopping.entity.management.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrdersMapper extends Mapper<Orders> {

    @Select("SELECT o.id,o.order_status,o.date,o.order_num,o.goods_id,u.user_name,g.goods_name FROM orders o LEFT JOIN " +
            "userinfo u ON u.id=o.user_id LEFT JOIN goods g ON g.id=o.goods_id LEFT JOIN shopgoods s ON g.id=s.goods_id " +
            "WHERE s.shop_id=#{shopId} AND o.order_status='1' ORDER BY o.date DESC")
    @Results(id = "ordersInfo",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "order_status",property = "orderStatus"),
            @Result(column = "order_num",property = "orderNum"),
            @Result(column = "goods_id",property = "goodsId"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "goods_name",property = "goodsName"),
            @Result(column = "goods_id",property = "picture",
                    one = @One(select = "com.shopping.mapper.goods.PicturesMapper.queryPictureAddress"))
    })
    List<Orders> queryOrdersByShop(Integer shopId,String status);

    @Select("SELECT * FROM orders  WHERE user_id = #{userId} AND STATUS LIKE #{status}")
    @Results(id = "bigOrder",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "id",property = "orderItems",
                    many = @Many(select = "com.shopping.mapper.management.OrderItemMapper.queryItemsByOrderId"))
    })
    List<Orders> queryOrdersByUser(Integer userId,String status);

    @Select("SELECT * FROM orders  WHERE id = #{id}")
    @ResultMap(value = "bigOrder")
    Orders queryOrdersById(Integer id);

}

package com.shopping.mapper.management;

import cn.hutool.db.sql.Order;
import com.shopping.entity.management.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrdersMapper extends Mapper<Orders> {



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

    @Select("SELECT DISTINCT o.id,o.user_id,o.date,o.status FROM orders o LEFT JOIN orderitem oi ON o.id=oi.order_id LEFT JOIN shopgoods sg ON sg.goods_id=oi.goods_id \n" +
            "             WHERE sg.shop_id = #{shopId} AND o.status LIKE #{status}")
    @ResultMap(value = "bigOrder")
    List<Orders> queryOrdersByShop(Integer shopId,String status);

}

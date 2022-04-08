package com.shopping.mapper.management;

import com.shopping.entity.management.OrderItem;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderItemMapper extends Mapper<OrderItem> {

    @Select("SELECT oi.*,p.address,g.goods_name FROM orderitem oi LEFT JOIN orders o ON o.id = oi.order_id " +
            " LEFT JOIN goods g ON oi.goods_id = g.id LEFT JOIN pictures p ON g.id = p.goods_id WHERE  " +
            " p.status = 1 AND oi.order_id = #{orderId}")
    @Results(id = "orderItem",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "order_id",property = "orderId"),
            @Result(column = "goods_id",property = "goodsId"),
            @Result(column = "goods_name",property = "goodsName"),
            @Result(column = "address",property = "picture")
    })
    List<OrderItem> queryItemsByOrderId(Integer orderId);
}

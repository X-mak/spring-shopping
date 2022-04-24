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

    @Select("SELECT oi.*,g.goods_name,g.status,g.picture FROM order_item oi LEFT JOIN goods_vw g ON g.id = oi.goods_id " +
            "WHERE oi.order_id=#{orderId}")
    @Results(id = "orderItem",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "order_id",property = "orderId"),
            @Result(column = "goods_id",property = "goodsId"),
            @Result(column = "goods_name",property = "goodsName"),
            @Result(column = "status",property = "goodsStatus"),
            @Result(column = "address",property = "picture")
    })
    List<OrderItem> queryItemsByOrderId(Integer orderId);
}

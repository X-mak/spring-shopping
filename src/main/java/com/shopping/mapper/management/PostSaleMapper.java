package com.shopping.mapper.management;

import com.shopping.entity.management.PostSale;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface PostSaleMapper extends Mapper<PostSale> {

    @Select("SELECT ps.* FROM post_sale ps LEFT JOIN order_item oi ON oi.id=ps.order_item_id " +
            "LEFT JOIN orders o ON o.id = oi.order_id WHERE o.user_id=#{buyerId} AND "+
            " ps.status LIKE #{status}")
    @Results(id = "saleForm",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "order_item_id",property = "orderItemId")
    })
    List<PostSale> queryPostSalesByBuyer(Integer buyerId,String status);

    @Select("SELECT ps.* FROM post_sale ps LEFT JOIN order_item oi ON oi.id=ps.order_item_id " +
            "LEFT JOIN shopgoods sg ON sg.goods_id=oi.goods_id " +
            "WHERE sg.shop_id = #{sellerId} AND ps.status LIKE #{status}")
    @ResultMap(value = "saleForm")
    List<PostSale> queryPostSalesBySeller(Integer sellerId,String status);
}

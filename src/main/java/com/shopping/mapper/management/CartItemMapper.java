package com.shopping.mapper.management;

import com.shopping.entity.management.CartItem;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CartItemMapper extends Mapper<CartItem> {

    @Select("SELECT ug.id,ug.user_id,ug.goods_id,property_value FROM user_goods ug " +
            "WHERE property_id=2 and ug.user_id = #{userId}")
    @Results(id = "cartWithGoods",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "goods_id",property = "goodsId"),
            @Result(column = "property_value",property = "num"),
            @Result(column = "goods_id",property = "goods",
                one = @One(select = "com.shopping.mapper.goods.GoodsMapper.querySimpleGoodsById"))
    })
    List<CartItem> queryCartByUserId(Integer userId);
}

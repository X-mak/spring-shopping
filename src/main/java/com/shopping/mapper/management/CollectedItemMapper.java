package com.shopping.mapper.management;

import com.shopping.entity.management.CollectedItem;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CollectedItemMapper extends Mapper<CollectedItem> {

    @Select("SELECT * FROM collection_item_vw where user_id = #{userId}")
    @Results(id = "collectedItem",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "goods_id",property = "goodsId"),
            @Result(column = "goods_id",property = "goods",
                    one = @One(select = "com.shopping.mapper.goods.GoodsMapper.querySimpleGoodsById"))
    })
    List<CollectedItem> queryCollectedById(Integer userId);

    @Select("SELECT COUNT(*) FROM user_goods WHERE user_id=#{userId} AND property_id=1")
    Integer queryIsCollected(Integer userId);
}

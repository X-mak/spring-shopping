package com.shopping.mapper.goods;

import com.shopping.entity.goods.Price;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface PriceMapper extends Mapper<Price> {

    @Select("select * from goods_price_vw where goods_id = #{goodsId}")
    @Results(id = "price",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "goods_id",property = "goodsId")
    })
    List<Price> queryPriceList(Integer goodsId);
}

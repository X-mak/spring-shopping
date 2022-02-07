package com.shopping.mapper.goods;

import com.shopping.entity.goods.Pictures;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface PicturesMapper extends Mapper<Pictures> {

    @Select("select * from pictures where goods_id=#{goodsId}")
    @Results(id = "singlePicture",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "goods_id",property = "goodsId"),
    })
    List<Pictures> queryPicturesByGoodsId(Integer goodsId);
}

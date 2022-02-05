package com.shopping.mapper.goods;

import com.shopping.entity.goods.Goods;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface GoodsMapper extends Mapper<Goods> {

    @Select("select sh.shop_name,g.*,c.class_name from goods g left join goodsclass gc on gc.goods_id=g.id " +
            "left join classes c on c.id=gc.class_id left join shopgoods sg on sg.goods_id=g.id left join " +
            "shop sh on sh.id = sg.shop_id " +
            "where (sh.shop_name like #{keyword} or g.goods_name like #{keyword} or c.class_name like #{keyword}) " +
            "and (c.id like #{classId} or c.id like #{subClassId}) AND sh.id LIKE #{shopId}" +
            "order by sg.date desc")
    @Results(id = "goodsInfo",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "shop_name",property = "shopName"),
            @Result(column = "goods_name",property = "goodsName"),
            @Result(column = "goods_status",property = "goodsStatus"),
            @Result(column = "class_name",property = "className")
    })
    List<Goods> querySelectedGoodsOrderByDate(String keyword,String classId,String subClassId,String shopId);

    @Select("select sh.shop_name,g.*,c.class_name from goods g left join goodsclass gc on gc.goods_id=g.id " +
            "left join classes c on c.id=gc.class_id left join shopgoods sg on sg.goods_id=g.id left join " +
            "shop sh on sh.id = sg.shop_id " +
            "where (sh.shop_name like #{keyword} or g.goods_name like #{keyword} or c.class_name like #{keyword}) " +
            "and (c.id like #{classId} or c.id like #{subClassId}) AND sh.id LIKE #{shopId}" +
            "order by g.sales desc")
    @ResultMap(value = "goodsInfo")
    List<Goods> querySelectedGoodsOrderBySales(String keyword,String classId,String subClassId,String shopId);

    @Select("select sh.shop_name,g.*,c.class_name from goods g left join goodsclass gc on gc.goods_id=g.id " +
            "left join classes c on c.id=gc.class_id left join shopgoods sg on sg.goods_id=g.id left join " +
            "shop sh on sh.id = sg.shop_id " +
            "where g.id=#{id} ")
    @ResultMap(value = "goodsInfo")
    Goods queryGoodsById(Integer id);
}

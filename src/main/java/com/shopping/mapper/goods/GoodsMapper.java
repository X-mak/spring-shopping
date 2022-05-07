package com.shopping.mapper.goods;

import com.shopping.entity.goods.Goods;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface GoodsMapper extends Mapper<Goods> {

    @Select("select * from goods_vw where id=#{id}")
    @Results(id = "simpleGoods",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "goods_name",property = "goodsName"),
            @Result(column = "class_name",property = "className")
    })
    Goods querySimpleGoodsById(Integer id);

    @Select("SELECT g.*,ge.value AS address,c.class_name FROM goods g LEFT JOIN goods_ex ge ON " +
            "g.id = ge.goods_id LEFT JOIN user_goods ug ON ug.goods_id=g.id " +
            "LEFT JOIN goods_class gc ON gc.goods_id=g.id LEFT JOIN classes c " +
            "ON c.id = gc.class_id WHERE ge.property_id=3 AND ge.status=#{status} " +
            "AND(g.goods_name LIKE #{keyword} OR c.class_name LIKE #{keyword} ) AND ug.property_id = 3 " +
            "AND ug.user_id like #{shopId} order by ug.date")
    @Results(id = "goodsInfo",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "shop_id",property = "shopId"),
            @Result(column = "goods_name",property = "goodsName"),
            @Result(column = "goods_status",property = "goodsStatus"),
            @Result(column = "class_name",property = "className"),
            @Result(column = "address",property = "picture")
    })
    List<Goods> querySelectedGoodsOrderByDate(String keyword,String shopId,String status);

    @Select("SELECT g.*,ge.value AS address,c.class_name FROM goods g LEFT JOIN goods_ex ge ON " +
            "g.id = ge.goods_id LEFT JOIN user_goods ug ON ug.goods_id=g.id " +
            "LEFT JOIN goods_class gc ON gc.goods_id=g.id LEFT JOIN classes c " +
            "ON c.id = gc.class_id WHERE ge.property_id=3 AND ge.status=#{status} " +
            "AND(g.goods_name LIKE #{keyword} OR c.class_name LIKE #{keyword} ) AND ug.property_id = 3 " +
            "AND ug.user_id like #{shopId} order by g.sales")
    @ResultMap(value = "goodsInfo")
    List<Goods> querySelectedGoodsOrderBySales(String keyword,String shopId,String status);

//    @Select("select sh.id AS shop_id,g.*,c.class_name from goods g left join goodsclass gc on gc.goods_id=g.id " +
//            "left join classes c on c.id=gc.class_id left join shopgoods sg on sg.goods_id=g.id left join " +
//            "shop sh on sh.id = sg.shop_id " +
//            "where (sh.shop_name like #{keyword} or g.goods_name like #{keyword} or c.class_name like #{keyword}) " +
//            "and (c.id like #{classId} or c.id like #{subClassId}) AND sh.id LIKE #{shopId} AND g.goods_status LIKE #{status}" +
//            "order by sg.date desc")
//    @Results(id = "goodsInfo",value = {
//            @Result(id = true,column = "id",property = "id"),
//            @Result(column = "shop_id",property = "shopId"),
//            @Result(column = "goods_name",property = "goodsName"),
//            @Result(column = "goods_status",property = "goodsStatus"),
//            @Result(column = "class_name",property = "className"),
//            @Result(column = "id",property = "picture",
//                    one = @One(select = "com.shopping.mapper.goods.PicturesMapper.queryPictureAddress"))
//    })
//    List<Goods> querySelectedGoodsOrderByDate(String keyword,String classId,String subClassId,String shopId,String status);

//    @Select("select sh.id AS shop_id,g.*,c.class_name from goods g left join goodsclass gc on gc.goods_id=g.id " +
//            "left join classes c on c.id=gc.class_id left join shopgoods sg on sg.goods_id=g.id left join " +
//            "shop sh on sh.id = sg.shop_id " +
//            "where (sh.shop_name like #{keyword} or g.goods_name like #{keyword} or c.class_name like #{keyword}) " +
//            "and (c.id like #{classId} or c.id like #{subClassId}) AND sh.id LIKE #{shopId} AND g.goods_status LIKE #{status}" +
//            "order by g.sales desc")
//    @ResultMap(value = "goodsInfo")
//    List<Goods> querySelectedGoodsOrderBySales(String keyword,String classId,String subClassId,String shopId,String status);

//    @Select("select sh.id AS shop_id,g.*,c.class_name from goods g left join goodsclass gc on gc.goods_id=g.id " +
//            "left join classes c on c.id=gc.class_id left join shopgoods sg on sg.goods_id=g.id left join " +
//            "shop sh on sh.id = sg.shop_id " +
//            "where g.id=#{id} ")
//    @Results(id = "goodsInfoWithPictures",value = {
//            @Result(id = true,column = "id",property = "id"),
//            @Result(column = "shop_id",property = "shopId"),
//            @Result(column = "goods_name",property = "goodsName"),
//            @Result(column = "goods_status",property = "goodsStatus"),
//            @Result(column = "class_name",property = "className"),
//            @Result(column = "id",property = "goodsPictures",
//                    many = @Many(
//                            select = "com.shopping.mapper.goods.PicturesMapper.queryPicturesByGoodsId"
//                    ))
//    })
//    Goods queryGoodsById(Integer id);

    @Select("SELECT * FROM goods_vw where id=#{id}")
    @Results(id = "goods_vw",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "goods_name",property = "goodsName"),
            @Result(column = "class_name",property = "className"),
            @Result(column = "id",property = "goodsPictures",
                    many = @Many(
                            select = "com.shopping.mapper.goods.PicturesMapper.queryPicturesByGoodsId"
                    ))
    })
    Goods queryGoodsById(Integer id);

    @Select("SELECT g.*,sc.num,p.address ,sh.id AS shop_id, " +
            "            CASE WHEN c.user_id= #{userId} THEN TRUE ELSE FALSE END AS collected  " +
            "            FROM cartitem sc LEFT JOIN goods g ON g.id = sc.goods_id LEFT JOIN pictures p ON  " +
            "            p.goods_id = g.id  LEFT JOIN collecteditem c ON c.goods_id=g.id LEFT JOIN  shopgoods sg ON " +
            "            sg.goods_id = g.id LEFT JOIN  shop sh ON sh.id = sg.shop_id " +
            "            WHERE sc.user_id = #{userId} AND p.status = 1")
    @Results(id = "goodsInCart",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "goods_name",property = "goodsName"),
            @Result(column = "goods_status",property = "goodsStatus"),
            @Result(column = "address",property = "picture"),
            @Result(column = "shop_id",property = "shopId")
    })
    List<Goods> queryGoodsInCart(Integer userId);


    @Select("SELECT g.*,p.address FROM collecteditem c LEFT JOIN goods g ON g.id = c.goods_id LEFT JOIN " +
            "pictures p ON p.goods_id = g.id WHERE c.user_id = #{userId}")
    @Results(id = "goodsInCollections",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "goods_name",property = "goodsName"),
            @Result(column = "goods_status",property = "goodsStatus"),
            @Result(column = "address",property = "picture")
    })
    List<Goods> queryGoodsInCollections(Integer userId);

    @Select("SELECT * FROM goods_vw")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "goods_name",property = "goodsName"),
            @Result(column = "class_name",property = "className")
    })
    List<Goods> test();
}

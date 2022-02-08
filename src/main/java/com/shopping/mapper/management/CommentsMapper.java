package com.shopping.mapper.management;

import com.shopping.entity.management.Comments;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CommentsMapper extends Mapper<Comments> {

    @Select("SELECT c.id,c.content,c.stars,c.date,c.hot,CASE WHEN c.user_id=#{userId} THEN 'true' ELSE 'false' END AS own," +
            "u.user_name,g.goods_name " +
            "FROM comments c LEFT JOIN userinfo u ON c.user_id=u.id LEFT JOIN goods g ON g.id=c.goods_id WHERE goods_id=#{goodsId} "+
            "ORDER BY c.date DESC")
    @Results(id = "commentsInfo",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "goods_name",property = "goodsName")
    })
    List<Comments> queryCommentsByDate(Integer userId,Integer goodsId);

    @Select("SELECT c.id,c.content,c.stars,c.date,c.hot,CASE WHEN c.user_id=#{userId} THEN 'true' ELSE 'false' END AS own," +
            "u.user_name,g.goods_name " +
            "FROM comments c LEFT JOIN userinfo u ON c.user_id=u.id LEFT JOIN goods g ON g.id=c.goods_id WHERE goods_id=#{goodsId} "+
            "ORDER BY c.hot DESC")
    @ResultMap(value = "commentsInfo")
    List<Comments> queryCommentsByHot(Integer userId,Integer goodsId);

    @Select("SELECT c.id,c.content,c.stars,c.date,c.hot,'' AS own,u.user_name,g.goods_name " +
            "FROM comments c LEFT JOIN userinfo u ON c.user_id=u.id LEFT JOIN goods g ON g.id=c.goods_id WHERE goods_id='1' " +
            "ORDER BY c.date DESC")
    @ResultMap(value = "commentsInfo")
    List<Comments> queryMyComments(Integer userId);


}

package com.shopping.inferior.management.comments.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.management.Comments;
import com.shopping.mapper.management.CommentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsServiceImp implements CommentsService{

    public int addComments(Comments comments){
        try{
            comments.setDate(DateUtil.now());
            comments.setHot(0);
            commentsMapper.insertSelective(comments);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int deleteComments(Integer id){
        try{
            commentsMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public PageInfo<Comments> getComments(Integer pageNum, Integer pageSize, Integer userId, Integer goodsId, String order){
        PageHelper.startPage(pageNum,pageSize,true);
        List<Comments> comments = new ArrayList<>();
        if(order.equals("date")){
            comments = commentsMapper.queryCommentsByDate(userId, goodsId);
        }else if(order.equals("hot")){
            comments = commentsMapper.queryCommentsByHot(userId, goodsId);
        }
        return new PageInfo<>(comments);
    }

    public PageInfo<Comments> getMyComments(Integer pageNum,Integer pageSize,Integer userId){
        PageHelper.startPage(pageNum,pageSize,true);
        List<Comments> comments = new ArrayList<>();
        comments = commentsMapper.queryMyComments(userId);
        return new PageInfo<>(comments);
    }

    public int changeComments(Comments comments){
        try{
            commentsMapper.updateByPrimaryKeySelective(comments);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }



    @Autowired
    CommentsMapper commentsMapper;
}

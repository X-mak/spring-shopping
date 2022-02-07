package com.shopping.inferior.management.comments.service;

import com.shopping.entity.management.Comments;

import java.util.List;

public interface CommentsService {

    int addComments(Comments comments);

    int deleteComments(Integer id);

    List<Comments> getComments(Integer pageNum,Integer pageSize,Integer userId,Integer goodsId);

    List<Comments> getMyComments(Integer pageNum,Integer pageSize,Integer userId);

    int changeComments(Comments comments);

}

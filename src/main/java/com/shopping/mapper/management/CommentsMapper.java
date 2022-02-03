package com.shopping.mapper.management;

import com.shopping.entity.management.Comments;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface CommentsMapper extends Mapper<Comments> {
}

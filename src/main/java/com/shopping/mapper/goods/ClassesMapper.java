package com.shopping.mapper.goods;

import com.shopping.entity.goods.Classes;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ClassesMapper extends Mapper<Classes> {

    @Select("SELECT * FROM classes WHERE id LIKE #{classId}")
    @Results(id = "classes",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "class_name",property = "className")
    })
    List<Classes> queryClasses(String classId);
}

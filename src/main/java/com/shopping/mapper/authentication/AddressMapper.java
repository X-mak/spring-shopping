package com.shopping.mapper.authentication;

import com.shopping.entity.authentication.Address;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AddressMapper extends Mapper<Address> {

    @Select("SELECT * FROM user_address_vw WHERE user_id=#{userId}")
    @Results(id = "address",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_id",property = "userId"),
            @Result(column = "address",property = "content"),
            @Result(column = "status",property = "addressStatus")
    })
    List<Address> queryAddress(Integer userId);
}

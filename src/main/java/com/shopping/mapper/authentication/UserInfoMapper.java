package com.shopping.mapper.authentication;

import com.shopping.entity.authentication.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
    @Select("SELECT u.*,a.content FROM userinfo u LEFT JOIN address a ON a.user_id=u.id LEFT JOIN useraccount ua ON ua.id=u.id " +
            "WHERE ua.account=#{userAccount} AND a.address_status=1")
    @Results(id = "loginUserInfo",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "user_status",property = "userStatus"),
            @Result(column = "user_phone",property = "userPhone"),
            @Result(column = "content",property = "address"),
            @Result(column = "id",property = "roleList",
                    many = @Many(select = "com.shopping.mapper.authentication.AccountRoleMapper.selectAccountRoleByAccount"))

    })
    UserInfo queryLoginUserInfo(String userAccount);

    @Select("SELECT u.*,a.content FROM userinfo u LEFT JOIN address a ON a.user_id=u.id " +
            "WHERE u.id=#{userId} AND a.address_status=1")
    @ResultMap(value = "loginUserInfo")
    UserInfo queryLoginUserInfoById(Integer userId);
}

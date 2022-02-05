package com.shopping.mapper.authentication;

import com.shopping.entity.authentication.UserInfo;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
    @Select("SELECT u.*,ur.role_name,a.content FROM userinfo u LEFT JOIN useraccount ua ON u.id=ua.id LEFT JOIN accountrole ar " +
            "ON ar.account_id=u.id LEFT JOIN userrole ur ON ar.role_id=ur.id LEFT JOIN address a ON a.user_id=u.id " +
            "WHERE ua.user_account=#{userAccount} AND a.address_status=1")
    @Results(id = "loginUserInfo",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "user_status",property = "userStatus"),
            @Result(column = "user_phone",property = "userPhone"),
            @Result(column = "role_name",property = "roleName"),
            @Result(column = "content",property = "address"),
    })
    UserInfo queryLoginUserInfo(String userAccount);

    @Select("SELECT u.*,ur.role_name,a.content FROM userinfo u LEFT JOIN useraccount ua ON u.id=ua.id LEFT JOIN accountrole ar " +
            "ON ar.account_id=u.id LEFT JOIN userrole ur ON ar.role_id=ur.id LEFT JOIN address a ON a.user_id=u.id " +
            "WHERE u.id=#{userId} AND a.address_status=1")
    @ResultMap(value = "loginUserInfo")
    UserInfo queryLoginUserInfoById(Integer userId);
}

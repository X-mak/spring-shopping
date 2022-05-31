package com.shopping.mapper.authentication;

import com.shopping.entity.authentication.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {
    @Select("SELECT u.id,u.user_name,u.user_status,u.user_phone,ue.value FROM user_info u " +
            "LEFT JOIN user_ex ue ON ue.user_id = u.id LEFT JOIN user_account ua ON ua.id=u.id " +
            "WHERE ue.property_id=1 AND ue.status=1 AND ua.account=#{userAccount}")
    @Results(id = "loginUserInfo",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "user_status",property = "userStatus"),
            @Result(column = "user_phone",property = "userPhone"),
            @Result(column = "value",property = "address"),
            @Result(column = "id",property = "roleList",
                    many = @Many(select = "com.shopping.mapper.authentication.AccountRoleMapper.selectAccountRoleByAccount"))

    })
    UserInfo queryLoginUserInfo(String userAccount);

    @Select("SELECT u.id,u.user_name,u.user_status,u.user_phone,ue.value FROM user_info u " +
            "LEFT JOIN user_ex ue ON ue.user_id = u.id WHERE " +
            "ue.property_id=1 AND ue.status=1 AND u.id=#{userId}")
    @ResultMap(value = "loginUserInfo")
    UserInfo queryLoginUserInfoById(Integer userId);

    @Select("SELECT ua.id,ua.account,u.user_name FROM user_info u LEFT JOIN user_account ua ON u.id=ua.id ")
    @Results(id = "basicUser",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "id",property = "roleList",
                    many = @Many(select = "com.shopping.mapper.authentication.AccountRoleMapper.selectAccountRoleByAccount"))
    })
    List<UserInfo> queryUsersBasicInfo();
}

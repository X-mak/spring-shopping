package com.shopping.mapper.authentication;

import com.shopping.entity.authentication.AccountRole;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AccountRoleMapper extends Mapper<AccountRole> {
    @Select("SELECT * FROM account_role WHERE account_id = #{userId}")
    @Results(value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "role_id",property = "roleId"),
            @Result(column = "account_id",property = "accountId")
    })
    List<AccountRole> selectAccountRoleByAccount(Integer userId);
}

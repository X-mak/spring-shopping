package com.shopping.mapper.authentication;

import com.shopping.entity.authentication.UserAccount;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserAccountMapper extends Mapper<UserAccount> {
}

package com.shopping.mapper.authentication;

import com.shopping.entity.authentication.AccountRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AccountRoleMapper extends Mapper<AccountRole> {
}

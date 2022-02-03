package com.shopping.mapper.authentication;

import com.shopping.entity.authentication.UserRole;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserRoleMapper extends Mapper<UserRole> {
}

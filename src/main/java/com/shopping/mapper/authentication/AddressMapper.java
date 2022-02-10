package com.shopping.mapper.authentication;

import com.shopping.entity.authentication.Address;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AddressMapper extends Mapper<Address> {
}

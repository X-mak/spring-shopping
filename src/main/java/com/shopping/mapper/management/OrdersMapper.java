package com.shopping.mapper.management;

import com.shopping.entity.management.Orders;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface OrdersMapper extends Mapper<Orders> {
}

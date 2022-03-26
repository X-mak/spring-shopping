package com.shopping.mapper.management;

import com.shopping.entity.management.OrderItem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface OrderItemMapper extends Mapper<OrderItem> {
}

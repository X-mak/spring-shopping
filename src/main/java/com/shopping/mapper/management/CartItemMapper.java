package com.shopping.mapper.management;

import com.shopping.entity.management.CartItem;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface CartItemMapper extends Mapper<CartItem> {
}

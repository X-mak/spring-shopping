package com.shopping.mapper.management;

import com.shopping.entity.management.ShoppingCart;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
@Repository
public interface ShoppingCartMapper extends Mapper<ShoppingCart> {
}

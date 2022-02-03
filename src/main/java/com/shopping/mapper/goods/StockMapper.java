package com.shopping.mapper.goods;

import com.shopping.entity.goods.Stock;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface StockMapper extends Mapper<Stock> {
}

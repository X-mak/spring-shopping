package com.shopping.mapper.goods;

import com.shopping.entity.goods.Goods;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface GoodsMapper extends Mapper<Goods> {
}

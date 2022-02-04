package com.shopping.inferior.goods.service;

import com.shopping.entity.goods.*;

import java.util.List;

public interface GoodsService {

    int addGoods(GoodsInfo goodsInfo);

    int deleteGoods(Integer goodsId);

    int changeGoodsBasicInfo(Goods goods);

    Goods getGoods(Integer goodsId);

    List<Goods> getGoodsBySearch(Integer pageNum,Integer pageSize,String keyword,String order,String classId,Integer shopId);

    int changePrice(Price price);

    int changeStock(Stock stock);

    int changeClass(GoodsClass goodsClass);

    List<Classes> getClasses(String classId);

    int addPictures(Integer goodsId,List<String> pictures);
}

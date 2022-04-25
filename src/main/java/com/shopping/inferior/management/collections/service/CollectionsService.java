package com.shopping.inferior.management.collections.service;

import com.github.pagehelper.PageInfo;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.management.CollectedItem;
import com.shopping.entity.management.Collections;

import java.util.List;

public interface CollectionsService {

    int collectMany(List<CollectedItem> collectedList);

    int cancelCollect(Integer id);

    PageInfo<Goods> getSelectedCollections(Integer pageNum,Integer pageSize);

    int isCollected(Integer goodsId);
}

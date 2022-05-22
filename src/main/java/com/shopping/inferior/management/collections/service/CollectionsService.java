package com.shopping.inferior.management.collections.service;

import com.github.pagehelper.PageInfo;
import com.shopping.entity.management.CollectedItem;

import java.util.List;

public interface CollectionsService {

    int collectMany(List<CollectedItem> collectedList);

    int cancelMultiCollect(List<Integer> collects);

    PageInfo<CollectedItem> getSelectedCollections(Integer pageNum,Integer pageSize);

    int isCollected(Integer goodsId);
}

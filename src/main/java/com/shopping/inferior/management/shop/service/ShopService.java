package com.shopping.inferior.management.shop.service;

import com.github.pagehelper.PageInfo;
import com.shopping.entity.management.Shop;

import java.util.List;

public interface ShopService {

    int addShop(Shop shop);

    int deleteShop(Integer id);

    int changeShop(Shop shop);

    PageInfo<Shop> getShopList(Integer pageNum, Integer pageSize, String userName, String shopName, String order);

    PageInfo<Shop> getMyShopList(Integer pageNum,Integer pageSize,Integer userId);
}

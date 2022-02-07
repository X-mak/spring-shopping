package com.shopping.inferior.management.shop.service;

import com.shopping.entity.management.Shop;

import java.util.List;

public interface ShopService {

    int addShop(Shop shop);

    int deleteShop(Integer id);

    int changeShop(Shop shop);

    List<Shop> getShopList(Integer pageNum,Integer pageSize,String userName,String shopName,String order);

    List<Shop> getMyShopList(Integer pageNum,Integer pageSize,Integer userId);
}

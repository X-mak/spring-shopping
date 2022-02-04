package com.shopping.entity.goods;

import lombok.Data;

import java.util.List;

@Data
public class GoodsInfo {
    private Integer id;
    private String goodsName;
    private Double price;
    private Integer stock;
    private Integer goodsStatus;
    private String classId;
    private String className;
    private Integer shopId;
    private Integer sales;
    private List<String> goodsPictures;

    public GoodsInfo(String goodsName, Double price, Integer stock, Integer goodsStatus, String classId, Integer shopId, List<String> goodsPictures) {
        this.goodsName = goodsName;
        this.price = price;
        this.stock = stock;
        this.goodsStatus = goodsStatus;
        this.classId = classId;
        this.shopId = shopId;
        this.goodsPictures = goodsPictures;
    }
}

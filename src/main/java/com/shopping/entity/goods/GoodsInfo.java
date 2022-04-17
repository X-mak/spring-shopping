package com.shopping.entity.goods;

import lombok.Data;

import java.util.List;

@Data
public class GoodsInfo {
    private Integer id; //主键，商品编号
    private String goodsName;   //商品名
    private Double price;   //商品价格
    private Integer stock;  //商品库存
    private Integer goodsStatus;    //商品状态，1为销售中，0为已下架
    private String classId; //类别编号
    private String className;   //类别名
    private Integer shopId; //商铺编号
    private String introduction;    //商品简介
    private Integer sales;  //销量
    private List<String> goodsPictures; //商品图片

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

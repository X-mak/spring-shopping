package com.shopping.entity.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，商品编号

    private String goodsName;   //商品名
    private Double price;   //商品价格
    private String size;    //尺寸大小
    private Integer stock;  //商品库存
    private Integer goodsStatus;    //商品状态，1为销售中，0为已下架
    private String introduction;    //商品简介
    private Integer sales;  //销量
    @Transient
    private Integer shopId;     //商铺编号
    @Transient
    private String className;   //类别
    @Transient
    private List<Pictures> goodsPictures;   //商品的所有图片
    @Transient
    private String picture; //商品的默认图片
    @Transient
    private Integer num;    //商品的数量
    @Transient
    private List<Price> priceList;  //价格变化


    public Goods(String goodsName, Double price, Integer stock, Integer goodsStatus) {
        this.goodsName = goodsName;
        this.price = price;
        this.stock = stock;
        this.goodsStatus = goodsStatus;
        this.sales = 0;
    }

    public Goods(String goodsName, Double price, Integer stock, Integer goodsStatus, String introduction) {
        this.goodsName = goodsName;
        this.price = price;
        this.stock = stock;
        this.goodsStatus = goodsStatus;
        this.introduction = introduction;
        this.sales = 0;
    }
}

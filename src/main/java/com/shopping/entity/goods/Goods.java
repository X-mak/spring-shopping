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
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，商品编号

    private String goodsName;   //商品名
    private Double price;   //商品价格
    private Integer stock;  //商品库存
    private Integer goodsStatus;    //商品状态，1为销售中，0为已下架
    private Integer sales;  //销量
    @Transient
    private String shopName;    //商铺名
    @Transient
    private String className;   //类别
    @Transient
    private List<Pictures> goodsPictures;   //商品的所有图片
    @Transient
    private String picture; //商品的默认图片
    @Transient
    private Integer num;    //商品的数量
    @Transient
    private Integer collected;  //商品收藏状态，1为已收藏，0为未收藏

    public Goods(String goodsName, Double price, Integer stock, Integer goodsStatus) {
        this.goodsName = goodsName;
        this.price = price;
        this.stock = stock;
        this.goodsStatus = goodsStatus;
        this.sales = 0;
    }
}

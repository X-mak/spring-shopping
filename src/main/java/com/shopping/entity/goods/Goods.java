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
    private Integer id;

    private String goodsName;
    private Double price;
    private Integer stock;
    private Integer goodsStatus;
    private Integer sales;
    @Transient
    private String shopName;
    @Transient
    private String className;
    @Transient
    private List<Pictures> goodsPictures;
    @Transient
    private String picture;
    @Transient
    private Integer num;
    @Transient
    private Integer collected;

    public Goods(String goodsName, Double price, Integer stock, Integer goodsStatus) {
        this.goodsName = goodsName;
        this.price = price;
        this.stock = stock;
        this.goodsStatus = goodsStatus;
        this.sales = 0;
    }
}

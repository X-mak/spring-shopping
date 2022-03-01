package com.shopping.entity.management;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "shopgoods")
public class ShopGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，商铺商品关联编号

    private Integer shopId; //商铺编号
    private Integer goodsId;    //商品编号
    private String date;    //上架日期

    public ShopGoods(Integer shopId, Integer goodsId) {
        this.shopId = shopId;
        this.goodsId = goodsId;
        this.date = DateUtil.now();
    }
}

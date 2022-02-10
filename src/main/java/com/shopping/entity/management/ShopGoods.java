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
    private Integer id;

    private Integer shopId;
    private Integer goodsId;
    private String date;

    public ShopGoods(Integer shopId, Integer goodsId) {
        this.shopId = shopId;
        this.goodsId = goodsId;
        this.date = DateUtil.now();
    }
}

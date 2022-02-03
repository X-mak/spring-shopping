package com.shopping.entity.management;

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

    public ShopGoods(Integer shopId, Integer goodsId) {
        this.shopId = shopId;
        this.goodsId = goodsId;
    }
}

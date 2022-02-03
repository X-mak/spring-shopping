package com.shopping.entity.goods;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer goodsId;
    private Double price;
    private String date;

    public Price(Integer goodsId, Double price) {
        this.goodsId = goodsId;
        this.price = price;
        this.date = DateUtil.now();
    }
}

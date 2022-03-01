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
    private Integer id; //主键，价格编号

    private Integer goodsId;    //商品编号
    private Double price;   //价格
    private String date;    //变动日期

    public Price(Integer goodsId, Double price) {
        this.goodsId = goodsId;
        this.price = price;
        this.date = DateUtil.now();
    }
}

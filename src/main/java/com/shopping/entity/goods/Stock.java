package com.shopping.entity.goods;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，库存编号

    private Integer goodsId;    //商品编号
    private Integer num;    //商品数量
    private String date;    //变动日期

    public Stock(Integer goodsId, Integer num) {
        this.goodsId = goodsId;
        this.num = num;
        this.date = DateUtil.now();
    }
}

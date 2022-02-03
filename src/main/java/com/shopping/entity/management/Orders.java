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
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer goodsId;
    private Integer orderNum;
    private String date;
    private Integer orderStatus;

    public Orders(Integer userId, Integer goodsId, Integer orderNum, Integer orderStatus) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.orderNum = orderNum;
        this.orderStatus = orderStatus;
        this.date = DateUtil.now();
    }
}

package com.shopping.entity.data;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user_goods")
public class UserGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer goodsId;
    private Integer propertyId;
    private String propertyValue;
    private String date;

    public UserGoods(Integer userId, Integer goodsId, Integer propertyId) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.propertyId = propertyId;
    }

    public UserGoods(Integer userId, Integer goodsId, Integer propertyId, String propertyValue) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.propertyId = propertyId;
        this.propertyValue = propertyValue;
        this.date = DateUtil.now();
    }
}

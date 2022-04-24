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
@Table(name = "goods_ex")
public class GoodsEx {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer goodsId;
    private Integer propertyId;
    private String value;
    private String date;
    private Integer status;

    public GoodsEx(Integer goodsId, Integer propertyId, String value, Integer status) {
        this.goodsId = goodsId;
        this.propertyId = propertyId;
        this.value = value;
        this.status = status;
        this.date = DateUtil.now();
    }
}

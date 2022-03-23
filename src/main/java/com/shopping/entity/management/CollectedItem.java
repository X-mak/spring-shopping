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
@Table(name = "collecteditem")
public class CollectedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，收藏品编号

    private Integer userId; //用户编号
    private Integer goodsId;    //商品编号
    private String date;    //收藏日期

    public CollectedItem(Integer userId, Integer goodsId) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.date = DateUtil.now();
    }
}

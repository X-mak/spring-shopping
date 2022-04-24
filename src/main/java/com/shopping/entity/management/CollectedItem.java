package com.shopping.entity.management;

import cn.hutool.core.date.DateUtil;
import com.shopping.entity.goods.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "collected_item")
public class CollectedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，收藏品编号

    private Integer userId; //用户编号
    private Integer goodsId;    //商品编号
    private String date;    //收藏日期
    @Transient
    private Goods goods;

    public CollectedItem(Integer userId, Integer goodsId) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.date = DateUtil.now();
    }
}

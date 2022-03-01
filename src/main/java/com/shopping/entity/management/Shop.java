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
@Table(name = "shop")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，商铺编号

    private String shopName;    //商铺姓名
    private Integer hot;    //商铺热度
    private String date;    //商铺日期

    public Shop(String shopName) {
        this.shopName = shopName;
        this.hot = 0;
        this.date = DateUtil.now();
    }

    public Shop(Integer id) {
        this.id = id;
        this.hot = 0;
        this.date = DateUtil.now();
    }
}

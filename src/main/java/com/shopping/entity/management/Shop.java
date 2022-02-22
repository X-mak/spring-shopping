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
    private Integer id;

    private String shopName;
    private Integer hot;
    private String date;

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

package com.shopping.entity.management;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "usershop")
public class UserShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，用户商铺关联编号

    private Integer userId; //用户编号
    private Integer shopId; //商铺编号

    public Integer getUserId() {
        return userId;
    }

    public Integer getShopId() {
        return shopId;
    }
}

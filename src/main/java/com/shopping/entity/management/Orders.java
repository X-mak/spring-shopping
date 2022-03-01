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
    private Integer id; //主键，订单编号

    private Integer userId; //用户编号
    private Integer goodsId;    //商品编号
    private Integer orderNum;   //订单数量
    private String date;    //订单日期
    private Integer orderStatus;    //订单状态
    @Transient
    private String userName;    //下单人姓名
    @Transient
    private String goodsName;   //购买的商品名
    @Transient
    private String picture; //购买的商品的默认图片地址

    public Orders(Integer userId, Integer goodsId, Integer orderNum, Integer orderStatus) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.orderNum = orderNum;
        this.orderStatus = orderStatus;
        this.date = DateUtil.now();
    }
}

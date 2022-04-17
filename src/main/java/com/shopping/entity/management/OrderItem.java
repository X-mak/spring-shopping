package com.shopping.entity.management;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orderitem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，订单商品编号

    private Integer orderId;    //订单编号
    private Integer goodsId;    //商品编号
    private Integer num;    //商品数量
    private Double price;   //单件商品价格
    @Transient
    private String picture; //商品图片
    @Transient
    private Integer shopId; //店铺编号
    @Transient
    private String goodsName; //商品名

}

package com.shopping.entity.management;

import cn.hutool.core.date.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private String date;    //订单日期
    private Integer status;    //订单状态
    @Transient
    private String userName;    //下单人姓名
    @Transient
    private List<OrderItem> orderItems; //订单项

    public Orders(Integer userId, Integer status) {
        this.userId = userId;
        this.status = status;
        this.date = DateUtil.now();
    }
}

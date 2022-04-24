package com.shopping.entity.management;

import com.shopping.entity.goods.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "cartitem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，购物车编号

    private Integer userId; //用户编号
    private Integer goodsId;    //商品编号
    private Integer num;    //购物车数量
    @Transient
    private Goods goods;  //商品

    public CartItem(Integer userId, Integer goodsId, Integer num) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.num = num;
    }
}

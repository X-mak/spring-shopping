package com.shopping.entity.management;

import com.shopping.entity.goods.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "post_sale")
public class PostSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，售后申请编号

    private Integer orderItemId;    //订单项编号
    private String topic;   //标题
    private String introduction;    //介绍
    private String picture; //图片
    private Integer status; //申请状态

    @Transient
    private Goods goods;    //商品

    public PostSale(Integer orderItemId, String topic, String introduction, String picture) {
        this.orderItemId = orderItemId;
        this.topic = topic;
        this.introduction = introduction;
        this.picture = picture;
        this.status = 0;
    }
}

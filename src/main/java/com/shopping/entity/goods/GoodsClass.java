package com.shopping.entity.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "goods_class")
public class GoodsClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，商品类别关联编号

    private Integer goodsId;    //商品编号
    private String classId; //类别编号

    public GoodsClass(Integer goodsId, String classId) {
        this.goodsId = goodsId;
        this.classId = classId;
    }
}

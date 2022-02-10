package com.shopping.entity.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "goodsclass")
public class GoodsClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer goodsId;
    private String classId;

    public GoodsClass(Integer goodsId, String classId) {
        this.goodsId = goodsId;
        this.classId = classId;
    }
}

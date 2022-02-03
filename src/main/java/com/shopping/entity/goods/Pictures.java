package com.shopping.entity.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "pictures")
public class Pictures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer goodsId;
    private String address;

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

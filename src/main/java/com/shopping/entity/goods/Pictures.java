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
    private Integer status;

    public Pictures(Integer goodsId, String address) {
        this.goodsId = goodsId;
        this.address = address;
    }

    public Pictures(Integer goodsId, String address, Integer status) {
        this.goodsId = goodsId;
        this.address = address;
        this.status = status;
    }
}

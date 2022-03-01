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
    private Integer id; //主键，图片编号

    private Integer goodsId;    //商品编号
    private String address; //图片地址
    private Integer status; //图片状态，1为默认地址，0为备用地址

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

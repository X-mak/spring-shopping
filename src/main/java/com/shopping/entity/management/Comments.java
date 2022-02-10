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
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer goodsId;
    private String content;
    private Integer stars;
    private String date;
    private Integer hot;
    @Transient
    private String userName;
    @Transient
    private String goodsName;
    @Transient
    private String own;

    public Comments(Integer userId, Integer goodsId, String content, Integer stars) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.content = content;
        this.stars = stars;
        this.date = DateUtil.now();
        this.hot = 0;
    }
}

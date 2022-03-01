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
    private Integer id; //主键，评论编号

    private Integer userId; //用户编号
    private Integer goodsId;    //商品编号
    private String content; //评论内容
    private Integer stars;  //评论星数
    private String date;    //评论日期
    private Integer hot;    //评论热度
    @Transient
    private String userName;    //评论人姓名
    @Transient
    private String goodsName;   //评论的商品名
    @Transient
    private String own; //评论所有权，1为自己的评论，0为他人的评论

    public Comments(Integer userId, Integer goodsId, String content, Integer stars) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.content = content;
        this.stars = stars;
        this.date = DateUtil.now();
        this.hot = 0;
    }
}

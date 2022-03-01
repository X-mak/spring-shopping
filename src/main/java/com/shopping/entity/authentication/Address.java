package com.shopping.entity.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，地址编号

    private Integer userId; //用户编号
    private String content; //具体地址内容
    private Integer addressStatus;  //地址状态，1为默认地址，0为备用地址

    public Address(Integer userId, String content, Integer addressStatus) {
        this.userId = userId;
        this.content = content;
        this.addressStatus = addressStatus;
    }
}

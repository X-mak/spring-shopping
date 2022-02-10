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
    private Integer id;

    private Integer userId;
    private String content;
    private Integer addressStatus;

    public Address(Integer userId, String content, Integer addressStatus) {
        this.userId = userId;
        this.content = content;
        this.addressStatus = addressStatus;
    }
}

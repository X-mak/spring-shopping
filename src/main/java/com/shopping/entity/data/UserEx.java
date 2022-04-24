package com.shopping.entity.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user_ex")
public class UserEx {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;
    private Integer propertyId;
    private String value;
    private Integer status;

    public UserEx(Integer userId, Integer propertyId,  String value,Integer status) {
        this.userId = userId;
        this.propertyId = propertyId;
        this.status = status;
        this.value = value;
    }
}

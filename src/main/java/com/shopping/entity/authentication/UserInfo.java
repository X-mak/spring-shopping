package com.shopping.entity.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "userinfo")
public class UserInfo {
    @Id
    private Integer id;

    private String userName;
    private Integer userStatus;
    private String userPhone;
}

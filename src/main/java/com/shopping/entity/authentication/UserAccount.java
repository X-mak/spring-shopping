package com.shopping.entity.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "useraccount")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，用户账号编号

    private String account; //用户账号
    private String pwd; //用户密码，已用SHA-256加密

    public UserAccount(String account, String pwd) {
        this.account = account;
        this.pwd = pwd;
    }
}

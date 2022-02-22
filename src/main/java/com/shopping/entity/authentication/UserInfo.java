package com.shopping.entity.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

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

    @Transient
    private List<AccountRole> roleList;
    @Transient
    private String address;
    @Transient
    private String token;

    public UserInfo(Integer id, String userName, String userPhone) {
        this.id = id;
        this.userName = userName;
        this.userPhone = userPhone;
    }
}

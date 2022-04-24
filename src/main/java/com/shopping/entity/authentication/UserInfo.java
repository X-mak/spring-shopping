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
@Table(name = "user_info")
public class UserInfo {
    @Id
    private Integer id; //主键，用户编号

    private String userName;    //用户姓名
    private Integer userStatus; //用户状态，1为已审核，0为未审核
    private String userPhone;   //用户电话号码

    @Transient
    private List<AccountRole> roleList; //用户权限列表
    @Transient
    private String address; //用户默认地址
    @Transient
    private String token;   //用户当前token

    public UserInfo(Integer id, String userName, String userPhone) {
        this.id = id;
        this.userName = userName;
        this.userPhone = userPhone;
    }
}

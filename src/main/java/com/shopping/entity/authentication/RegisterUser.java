package com.shopping.entity.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser {
    //用户账号
    private String userAccount;
    //用户密码
    private String pwd;
    //用户姓名
    private String userName;
    //用户状态，只有卖家需要，1为通过审核，0为未审核
    private Integer userStatus;
    //用户电话号码
    private String userPhone;
    //用户角色，为seller/buyer/admin
    private String userRole;
    //用户默认地址
    private String address;
}

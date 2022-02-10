package com.shopping.entity.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser {

    private String userAccount;
    private String pwd;
    private String userName;
    private Integer userStatus;
    private String userPhone;
    private String userRole;
    private String address;
}

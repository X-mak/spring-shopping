package com.shopping.inferior.authentication.service;

import com.shopping.entity.authentication.Address;
import com.shopping.entity.authentication.RegisterUser;
import com.shopping.entity.authentication.UserAccount;
import com.shopping.entity.authentication.UserInfo;

public interface AuthenticationService {

    //-1为数据库出错，0为重复账号，1为正常执行
    int addUser(RegisterUser registerUser);

    //null为登陆失败
    UserInfo checkLogin(UserAccount userAccount);

    //-1为数据库出错，0为账号或密码错误，1为正常执行
    int changePwd(UserAccount userAccount,String newPwd);

    //null为没有此账号
    UserInfo getUserById(Integer userId);

    int changeBasicInfo(UserInfo userInfo);

    int addAddress(Address address);

    int changeAddress(Address address);
}

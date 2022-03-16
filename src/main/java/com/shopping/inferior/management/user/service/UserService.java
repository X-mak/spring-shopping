package com.shopping.inferior.management.user.service;

import com.github.pagehelper.PageInfo;
import com.shopping.entity.authentication.Address;
import com.shopping.entity.authentication.UserAccount;
import com.shopping.entity.authentication.UserInfo;

public interface UserService {

    //null为没有此账号
    UserInfo getUserById(Integer userId);

    int changeBasicInfo(UserInfo userInfo);

    int addAddress(Address address);

    int changeAddress(Address address);

    PageInfo<Address> getAddress(Integer userId,Integer pageNum,Integer pageSize);

    int deleteAddress(Integer id);

    PageInfo<UserInfo> getUsers(Integer pageNum,Integer pageSize);
}

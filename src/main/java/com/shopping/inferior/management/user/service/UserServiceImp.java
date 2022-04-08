package com.shopping.inferior.management.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.authentication.Address;
import com.shopping.entity.authentication.UserInfo;
import com.shopping.mapper.authentication.*;
import com.shopping.mapper.management.ShopMapper;
import com.shopping.utils.Encode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    public UserInfo getUserById(Integer userId){
        return userInfoMapper.queryLoginUserInfoById(userId);
    }

    public int changeBasicInfo(UserInfo userInfo){
        try{
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }


    public int addAddress(Address address){
        try{
            addressMapper.insertSelective(address);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }


    public int changeAddress(Address address) {
        try{
            addressMapper.updateByPrimaryKeySelective(address);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public PageInfo<Address> getAddress(Integer userId,Integer pageNum,Integer pageSize){
        //分页
        PageHelper.startPage(pageNum,pageSize,true);

        List<Address> addresses = addressMapper.queryAddress(userId);
        return new PageInfo<>(addresses);
    }

    public int deleteAddress(Integer id){
        try{
            Address address = addressMapper.selectByPrimaryKey(id);
            if(address.getAddressStatus()==1)return 0;
            addressMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public PageInfo<UserInfo> getUsers(Integer pageNum,Integer pageSize){
        //分页
        PageHelper.startPage(pageNum,pageSize,true);

        List<UserInfo> userInfos = userInfoMapper.queryUsersBasicInfo();
        return new PageInfo<>(userInfos);
    }

    @Autowired
    AddressMapper addressMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
}

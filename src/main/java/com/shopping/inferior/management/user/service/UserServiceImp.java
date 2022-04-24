package com.shopping.inferior.management.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.authentication.Address;
import com.shopping.entity.authentication.UserInfo;
import com.shopping.entity.data.UserEx;
import com.shopping.mapper.authentication.*;
import com.shopping.mapper.data.UserExMapper;
import com.shopping.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            Integer userId = TokenUtils.getLoginUser().getId();
            userExMapper.insertSelective(new UserEx(userId,1,address.getContent(),address.getAddressStatus()));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }


    public int changeAddress(Address address) {
        try{
            Integer userId = TokenUtils.getLoginUser().getId();
            userExMapper.updateByPrimaryKeySelective(new UserEx(address.getId(),userId,1,address.getContent(),address.getAddressStatus()));
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
            UserEx userEx = userExMapper.selectByPrimaryKey(id);
            if(userEx.getStatus()==1)return 0;
            userExMapper.deleteByPrimaryKey(id);
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
    @Autowired
    UserExMapper userExMapper;
}

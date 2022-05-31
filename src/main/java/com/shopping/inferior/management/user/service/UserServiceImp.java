package com.shopping.inferior.management.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.authentication.Address;
import com.shopping.entity.authentication.UserInfo;
import com.shopping.entity.data.UserEx;
import com.shopping.entity.data.UserGoods;
import com.shopping.entity.goods.Goods;
import com.shopping.mapper.authentication.*;
import com.shopping.mapper.data.UserExMapper;
import com.shopping.mapper.data.UserGoodsMapper;
import com.shopping.mapper.goods.GoodsMapper;
import com.shopping.utils.AccessControlUtil;
import com.shopping.utils.TokenUtils;
import org.checkerframework.checker.units.qual.A;
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
            if(!accessControlUtil.controlInUser(userInfo.getId()))return -1;
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
            if(userId != address.getUserId())return -1;
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
            if(userId != address.getUserId())return -1;
            Example example = new Example(UserEx.class);
            example.createCriteria().andEqualTo("userId",userId)
                    .andEqualTo("status",1);
            UserEx userEx = new UserEx();
            userEx.setStatus(0);
            userExMapper.updateByExampleSelective(userEx,example);
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
            Integer userId = TokenUtils.getLoginUser().getId();

            UserEx userEx = userExMapper.selectByPrimaryKey(id);
            if(userEx.getStatus()==1)return 0;
            if(userEx.getUserId() != userId)return -1;
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

    public int banUser(Integer userId){
        try{
            UserInfo userInfo = new UserInfo();
            userInfo.setId(userId);userInfo.setUserStatus(-1);
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
            Example example = new Example(UserGoods.class);
            example.createCriteria().andEqualTo("userId",userId)
                    .andEqualTo("propertyId",3);
            List<UserGoods> userGoods = userGoodsMapper.selectByExample(example);
            for(UserGoods ug:userGoods){
                Integer goodsId = ug.getGoodsId();
                Goods goods = new Goods();goods.setId(goodsId);goods.setGoodsStatus(-1);
                goodsMapper.updateByPrimaryKeySelective(goods);
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return  1;
    }

    @Autowired
    AddressMapper addressMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    UserExMapper userExMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserGoodsMapper userGoodsMapper;
    @Autowired
    AccessControlUtil accessControlUtil;
}

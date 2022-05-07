package com.shopping.utils;

import com.shopping.entity.data.GoodsEx;
import com.shopping.entity.data.UserEx;
import com.shopping.entity.data.UserGoods;
import com.shopping.entity.management.Orders;
import com.shopping.mapper.data.GoodsExMapper;
import com.shopping.mapper.data.UserGoodsMapper;
import com.shopping.mapper.management.OrdersMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Component
public class AccessControlUtil {

    public boolean controlInUserGoods(Integer goodsId,Integer status){
        Integer id = TokenUtils.getLoginUser().getId();
        Example example = new Example(UserGoods.class);
        example.createCriteria().andEqualTo("userId",id)
                .andEqualTo("goodsId",goodsId)
                .andEqualTo("propertyId",status);
        List<UserGoods> userGoods = userGoodsMapper.selectByExample(example);

        return userGoods.size() != 0;
    }

    public boolean controlInUserGoods(Integer goodsId,Integer status,Integer userId){
        Example example = new Example(UserGoods.class);
        example.createCriteria().andEqualTo("userId",userId)
                .andEqualTo("goodsId",goodsId)
                .andEqualTo("propertyId",status);
        List<UserGoods> userGoods = userGoodsMapper.selectByExample(example);

        return userGoods.size() == 0;
    }

    public boolean controlInId(Integer id,Integer status){
        UserGoods userGoods = userGoodsMapper.selectByPrimaryKey(id);
        Integer userId = TokenUtils.getLoginUser().getId();
        return userId == userGoods.getUserId() && status == userGoods.getPropertyId();
    }

    public boolean controlInId(Integer id,Integer status,Integer userId){
        UserGoods userGoods = userGoodsMapper.selectByPrimaryKey(id);
        return userId == userGoods.getUserId() && status == userGoods.getPropertyId();
    }

    public boolean controlInOrder(Integer id){
        Integer userId = TokenUtils.getLoginUser().getId();
        Orders orders = ordersMapper.selectByPrimaryKey(id);
        return userId == orders.getUserId();
    }

    public boolean controlInUser(Integer id){
        Integer userId = TokenUtils.getLoginUser().getId();
        return userId == id;
    }


    @Autowired
    UserGoodsMapper userGoodsMapper;
    @Autowired
    OrdersMapper ordersMapper;

}

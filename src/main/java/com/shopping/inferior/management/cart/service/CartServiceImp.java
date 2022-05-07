package com.shopping.inferior.management.cart.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.data.UserGoods;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.management.CartItem;
import com.shopping.mapper.data.UserGoodsMapper;
import com.shopping.mapper.goods.GoodsMapper;
import com.shopping.mapper.management.CartItemMapper;
import com.shopping.utils.AccessControlUtil;
import com.shopping.utils.TokenUtils;
import org.apache.catalina.User;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class CartServiceImp implements CartService{

    public int addCartGoods(Integer goodsId,Integer num){
        Integer userId = TokenUtils.getLoginUser().getId();
        try{
            userGoodsMapper.insertSelective(new UserGoods(userId,goodsId,2,num+""));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int deleteCartGoods(Integer id){
        try{
            if(!accessControlUtil.controlInId(id,2))return -1;

            userGoodsMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int updateCartGoods(Integer id,Integer num){
        try{
            if(!accessControlUtil.controlInId(id,2))return -1;

            UserGoods userGoods = new UserGoods();
            userGoods.setId(id);userGoods.setPropertyValue(num+"");
            userGoodsMapper.updateByPrimaryKeySelective(userGoods);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public PageInfo<CartItem> getCartGoods(Integer pageNum,Integer pageSize){
        Integer id = TokenUtils.getLoginUser().getId();
        PageHelper.startPage(pageNum,pageSize,true);
        List<CartItem> cartItems = cartItemMapper.queryCartByUserId(id);
        return new PageInfo<>(cartItems);
    }

    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserGoodsMapper userGoodsMapper;
    @Autowired
    AccessControlUtil accessControlUtil;
}

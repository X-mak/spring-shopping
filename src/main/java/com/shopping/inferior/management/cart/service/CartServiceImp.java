package com.shopping.inferior.management.cart.service;

import com.shopping.entity.management.ShoppingCart;
import com.shopping.mapper.management.ShoppingCartMapper;
import com.shopping.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImp {

    public int addCartGoods(Integer goodsId){
        Integer userId = TokenUtils.getLoginUser().getId();
        try{
            shoppingCartMapper.insertSelective(new ShoppingCart(userId, goodsId));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int deleteCartGoods(Integer id){
        try{
            shoppingCartMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int updateCartGoods(Integer id,Integer num){
        try{
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setId(id);
            shoppingCart.setNum(num);
            shoppingCartMapper.updateByPrimaryKeySelective(shoppingCart);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Autowired
    ShoppingCartMapper shoppingCartMapper;
}

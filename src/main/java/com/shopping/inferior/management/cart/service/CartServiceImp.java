package com.shopping.inferior.management.cart.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.management.CartItem;
import com.shopping.entity.management.ShoppingCart;
import com.shopping.mapper.goods.GoodsMapper;
import com.shopping.mapper.management.CartItemMapper;
import com.shopping.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService{

    public int addCartGoods(Integer goodsId,Integer num){
        Integer userId = TokenUtils.getLoginUser().getId();
        try{
            cartItemMapper.insertSelective(new CartItem(userId,goodsId,num));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int deleteCartGoods(Integer id){
        try{
            cartItemMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int updateCartGoods(Integer id,Integer num){
        try{
//            ShoppingCart shoppingCart = new ShoppingCart();
//            shoppingCart.setId(id);
//            shoppingCart.setNum(num);
            CartItem cartItem = new CartItem();
            cartItem.setId(id);
            cartItem.setNum(num);
            cartItemMapper.updateByPrimaryKeySelective(cartItem);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public PageInfo<Goods> getCartGoods(Integer pageNum,Integer pageSize){
        Integer id = TokenUtils.getLoginUser().getId();
        PageHelper.startPage(pageNum,pageSize,true);
        List<Goods> goods = goodsMapper.queryGoodsInCart(id);
        return new PageInfo<>(goods);
    }

    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    GoodsMapper goodsMapper;
}

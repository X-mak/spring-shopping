package com.shopping.inferior.management.cart.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.management.ShoppingCart;
import com.shopping.mapper.goods.GoodsMapper;
import com.shopping.mapper.management.ShoppingCartMapper;
import com.shopping.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements CartService{

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

    public PageInfo<Goods> getCartGoods(Integer pageNum,Integer pageSize){
        Integer id = TokenUtils.getLoginUser().getId();
        PageHelper.startPage(pageNum,pageSize,true);
        List<Goods> goods = goodsMapper.queryGoodsInCart(id);
        return new PageInfo<>(goods);
    }

    @Autowired
    ShoppingCartMapper shoppingCartMapper;
    @Autowired
    GoodsMapper goodsMapper;
}

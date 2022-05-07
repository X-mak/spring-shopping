package com.shopping;

import com.shopping.entity.authentication.Address;
import com.shopping.entity.authentication.UserInfo;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.goods.Pictures;
import com.shopping.entity.management.CartItem;
import com.shopping.entity.management.Orders;
import com.shopping.inferior.management.logistic.service.LogisticService;
import com.shopping.mapper.authentication.AddressMapper;
import com.shopping.mapper.authentication.UserInfoMapper;
import com.shopping.mapper.goods.GoodsMapper;
import com.shopping.mapper.goods.PicturesMapper;
import com.shopping.mapper.management.CartItemMapper;
import com.shopping.mapper.management.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ShoppingApplicationTests {

    @Test
    void contextLoads() {
        String res = logisticService.getLogisticInfo("10",1);
        System.out.println(res);
    }

    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    PicturesMapper picturesMapper;
    @Autowired
    CartItemMapper cartItemMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    LogisticService logisticService;

}

package com.shopping;

import com.shopping.entity.management.Orders;
import com.shopping.mapper.management.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShoppingApplicationTests {

    @Test
    void contextLoads() {
        Orders orders = ordersMapper.queryOrdersById(1);
        System.out.println(orders);
    }

    @Autowired
    OrdersMapper ordersMapper;


}

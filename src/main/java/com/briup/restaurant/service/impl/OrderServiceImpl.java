package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Order;
import com.briup.restaurant.bean.OrderExample;
import com.briup.restaurant.bean.ex.Orderex;
import com.briup.restaurant.mapper.OrderMapper;
import com.briup.restaurant.mapper.ex.OrderEXMapper;
import com.briup.restaurant.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
   private OrderMapper orderMapper;
    @Autowired
    private OrderEXMapper orderEXMapper;

    @Override
    public Orderex selectPrice(int id) throws RuntimeException {
        OrderExample orderExample=new OrderExample();
        Orderex order=orderEXMapper.selectPrice(id);
        return order;
    }
}

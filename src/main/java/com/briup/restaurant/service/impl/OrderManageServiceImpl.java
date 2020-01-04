package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Order;
import com.briup.restaurant.mapper.OrderMapper;
import com.briup.restaurant.service.IOrderManageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderManageServiceImpl implements IOrderManageService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public List<Order> selectAll() throws RuntimeException {
        return ;
    }
}

package com.briup.restaurant.service;

import com.briup.restaurant.bean.Order;
import com.briup.restaurant.bean.ex.Orderex;

public interface IOrderService {
    Orderex selectPrice(int id) throws  RuntimeException;
}

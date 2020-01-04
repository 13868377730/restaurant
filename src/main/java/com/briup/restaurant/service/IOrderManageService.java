package com.briup.restaurant.service;

import com.briup.restaurant.bean.Order;

import java.util.List;

public interface IOrderManageService {
    List<Order> selectAll() throws RuntimeException;

}

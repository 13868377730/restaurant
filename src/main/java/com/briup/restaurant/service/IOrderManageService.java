package com.briup.restaurant.service;

import com.briup.restaurant.bean.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface IOrderManageService {
    List<Map<String, Object>> selectAll() throws RuntimeException;
    Map<String,Object> showDetailById(int id) throws RuntimeException;
    void updateTableById(int orderId,int tableId) throws RuntimeException;

}

package com.briup.restaurant.service;

import com.briup.restaurant.bean.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface IOrderManageService {
    List<Map<String, Object>> selectAll() throws RuntimeException;
    Map<String,Object> showDetailById(int id) throws RuntimeException;
    void updateTableById(int orderId,int tableId) throws RuntimeException;
    void discountById(int orderId,double price) throws RuntimeException;
    void deleteFoodById(int orderId, int foodId) throws RuntimeException;
    List<Map<String, Object>> searchByCon(String key,String word)throws RuntimeException;
    void deleteOrderById(int id) throws RuntimeException;
    void addOrUpdQRCodeById(int id) throws RuntimeException, JsonProcessingException;
}

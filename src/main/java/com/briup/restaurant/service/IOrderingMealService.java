package com.briup.restaurant.service;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.Order;
import com.briup.restaurant.bean.Table;

import java.util.List;

public interface IOrderingMealService {
    void InsertOrder(Order order) throws RuntimeException;
    List<Table> IsHasTable(int num) throws RuntimeException;
    void UpdateTable(Table table) throws RuntimeException;
    void InsertItems(Order order, Integer[] num) throws RuntimeException;
    List<Food> IsHasFood(Integer[] num) throws RuntimeException;
    boolean IsMatch(int id,String name) throws RuntimeException;
    double Check(Integer[] num) throws RuntimeException;
    Order SelectOrder(int id) throws RuntimeException;
    void UpdateAddFoodPrice(int id,double price) throws RuntimeException;
    boolean IsOrderExistence(int id)throws RuntimeException;
}

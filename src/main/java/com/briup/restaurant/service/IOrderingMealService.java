package com.briup.restaurant.service;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.Order;
import com.briup.restaurant.bean.Table;

import java.util.List;

public interface IOrderingMealService {
    void InsertOrder(Order order) throws RuntimeException;
    List<Table> IsHasTable(int num) throws RuntimeException;
    void UpdateTable(Table table) throws RuntimeException;
    void InsertItems(Order order, int[] num) throws RuntimeException;
    List<Food> IsHasFood(int[] num) throws RuntimeException;
    boolean IsMatch(int id,String name) throws RuntimeException;
    double Check(int[] num) throws RuntimeException;
}

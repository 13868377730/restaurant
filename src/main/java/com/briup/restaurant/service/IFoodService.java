package com.briup.restaurant.service;

import com.briup.restaurant.bean.Food;

import java.util.List;

public interface IFoodService {

    void addOrUpdateFood(Food food) throws  RuntimeException;
    List<Food> selectAll() throws  RuntimeException;
    List<Food> selectBy (String key1,String key2,String word) throws  RuntimeException;
     Food selectById(int id) throws  RuntimeException;
}

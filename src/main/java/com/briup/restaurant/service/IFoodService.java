package com.briup.restaurant.service;

import com.briup.restaurant.bean.Food;

import java.util.List;

public interface IFoodService {

    void addOrUpdateFood(Food food) throws  RuntimeException;
    List<Food> selectAll() throws  RuntimeException;
    List<Food> selectByFoodName (String name) throws  RuntimeException;

}

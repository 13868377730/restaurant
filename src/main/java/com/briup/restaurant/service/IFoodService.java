package com.briup.restaurant.service;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.ex.FoodSales;

import java.util.List;

public interface IFoodService {

    void addOrUpdateFood(Food food) throws  RuntimeException;
    List<Food> selectAll() throws  RuntimeException;
    List<Food> selectBy (String key1,String key2,String word) throws  RuntimeException;
     Food selectById(int id) throws  RuntimeException;
     List<Food> selectByState() throws  RuntimeException;
    List<FoodSales> selectSales(String date1, String date2) throws  RuntimeException;
    List<FoodSales> selectMonth()throws  RuntimeException;
    void deleteById(int id);
    void addOrUpdQRCode() throws RuntimeException;


}

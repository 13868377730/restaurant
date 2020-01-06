package com.briup.restaurant.mapper;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.FoodExample;
import java.util.List;

import com.briup.restaurant.bean.ex.FoodSales;
import org.apache.ibatis.annotations.Param;

public interface FoodMapper {
    long countByExample(FoodExample example);

    int deleteByExample(FoodExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Food record);

    int insertSelective(Food record);

    List<Food> selectByExample(FoodExample example);

    Food selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Food record, @Param("example") FoodExample example);

    int updateByExample(@Param("record") Food record, @Param("example") FoodExample example);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);

    List<Food>  selectBy(String key1,String key2,String word);
    List<FoodSales> selectSales(String date1,String date2);
    List<FoodSales> selectAll();
    List<FoodSales> selectAfter(String date1);
    List<FoodSales> selectBefore(String date2);
}
package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.FoodExample;
import com.briup.restaurant.bean.ex.FoodSales;
import com.briup.restaurant.mapper.FoodMapper;
import com.briup.restaurant.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceService implements IFoodService {

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public void addOrUpdateFood(Food food) throws RuntimeException {
        if (food==null){
            throw  new RuntimeException("参数为空");
        }
        if(food.getId()==null){
            foodMapper.insert(food);}
        else{foodMapper.updateByPrimaryKey(food);}
    }

    @Override
    public List<Food> selectAll() throws RuntimeException {
        FoodExample example=new FoodExample();
        return foodMapper.selectByExample(example);
    }

    @Override
    public List<Food> selectBy(String key1,String key2,String word) throws RuntimeException {

        if (key1==null||"".equals(key1)){
            key1="%%";
        }
        if (key2==null||"".equals(key2)){
            key2="%%";
        }
        if (word==null){
            word="";
        }
        word="%"+word+"%";

        List<Food> foods=  foodMapper.selectBy(key1,key2,word);
        return foods;
    }

    @Override
    public  Food selectById(int id) throws RuntimeException {
     Food food=   foodMapper.selectByPrimaryKey(id);
        return  food;
    }

    @Override
    public List<Food> selectByState() throws RuntimeException {
        FoodExample example=new FoodExample();
        example.createCriteria().andStateEqualTo("在架");
        List<Food> foods=foodMapper.selectByExample(example);
        return foods;
    }

    @Override
    public List<FoodSales> selectSales(String date1, String date2) {
     List<FoodSales> foodSales=   foodMapper.selectSales(date1,date2);
        return foodSales;
    }


}

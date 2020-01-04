package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.FoodExample;
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
    public List<Food> selectByFoodName(String name) throws RuntimeException {
        FoodExample example=new FoodExample();
        example.createCriteria().andNameEqualTo(name);
     List<Food>  foods=  foodMapper.selectByExample(example);

        return foods;
    }


}

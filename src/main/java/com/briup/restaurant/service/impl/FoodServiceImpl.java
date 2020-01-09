package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.FoodExample;
import com.briup.restaurant.bean.ex.FoodSales;
import com.briup.restaurant.mapper.FoodMapper;
import com.briup.restaurant.service.IFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class FoodServiceImpl implements IFoodService {

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
        if (foods.isEmpty()){ throw new RuntimeException("无此数据");}
        else{return  foods;}

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
        return  foods;


    }

    @Override
    public List<FoodSales> selectSales(String date1, String date2) throws  RuntimeException{


         if ((date1 == null || "".equals(date1)) && (date2 == null || "".equals(date2))) {
             List<FoodSales> foodSales = foodMapper.selectAll();

             return foodSales;
         }
        else if ((date1 != null || !"".equals(date1)) && (date2 == null || "".equals(date2))) {
             List<FoodSales> foodSales = foodMapper.selectAfter(date1);

             return foodSales;
         }
        else if ((date1 == null || "".equals(date1)) && (date2 != null || !"".equals(date2))) {
             List<FoodSales> foodSales = foodMapper.selectBefore(date2);

             return foodSales;
         }
       else if((date1!=null||!"".equals(date1))&&(date2!=null||!"".equals(date2))) {
            List<FoodSales> foodSales = foodMapper.selectSales(date1, date2);

            return foodSales;
        }
        return null;
    }

    @Override
    public List<FoodSales> selectMonth() throws  RuntimeException{
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return foodMapper.selectMonth(year,month);
    }

    @Override
    public void deleteById(int id) {
        foodMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addOrUpdQRCode() throws RuntimeException {


    }
}

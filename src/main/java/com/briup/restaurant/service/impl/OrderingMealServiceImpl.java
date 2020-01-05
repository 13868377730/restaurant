package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.*;
import com.briup.restaurant.mapper.FoodMapper;
import com.briup.restaurant.mapper.ItemMapper;
import com.briup.restaurant.mapper.OrderMapper;
import com.briup.restaurant.mapper.TableMapper;
import com.briup.restaurant.service.IOrderingMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderingMealServiceImpl implements IOrderingMealService {



    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public void InsertOrder(Order order) throws RuntimeException {
         orderMapper.insert(order);


    }

    @Override
    public List<Table> IsHasTable(int num) throws RuntimeException {
        TableExample example=new TableExample();
        example.createCriteria().andSeatingEqualTo(num).andStateEqualTo("空闲");
        List<Table> list=tableMapper.selectByExample(example);
        //System.out.println(list);
        return list;
    }

    @Override
    public void UpdateTable(Table table) throws RuntimeException {
        table.setState("忙碌");
        tableMapper.updateByPrimaryKey(table);

    }

    @Override
    public void InsertItems(Order order, int[] num) throws RuntimeException {
        for (int i: num){
            Item item=new Item();
            item.setFoodId(i);
            item.setOrderId(order.getId());
            item.setState("备餐中");
            System.out.println(item);
            itemMapper.insert(item);
        }
    }

    @Override
    public List<Food> IsHasFood(int[] num) throws RuntimeException {
        List<Food> list=new ArrayList<Food>();
        for (int i:num){
            if ("下架".equals(foodMapper.selectByPrimaryKey(i).getState())){
                list.add(foodMapper.selectByPrimaryKey(i));
            }
        }
        //返回已经下架的信息
        System.out.println(list);
        return list;
    }


}

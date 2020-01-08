package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.*;
import com.briup.restaurant.mapper.*;
import com.briup.restaurant.mapper.ex.OrderEXMapper;
import com.briup.restaurant.service.IOrderingMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderingMealServiceImpl implements IOrderingMealService {



    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private OrderEXMapper orderEXMapper;

    @Override
    public void InsertOrder(Order order) throws RuntimeException {
        orderEXMapper.InsertOrder(order);
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
    public void InsertItems(Order order, Integer[] num) throws RuntimeException {
        for (int i: num){
            Item item=new Item();
            item.setFoodId(i);
            item.setOrderId(order.getId());
            System.out.println(order.getId());
            item.setState("未开始");
            System.out.println(item);
            itemMapper.insert(item);
        }
    }

    @Override
    public List<Food> IsHasFood(Integer[] num) throws RuntimeException {
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

    @Override
    public boolean IsMatch(int id, String name) throws RuntimeException {
        //不存在id
        if (userMapper.selectByPrimaryKey(id)==null){
            System.out.println("不存在id");
            return false;
        }
        //存在id
        else{
            System.out.println("存在id");
            UserExample example=new UserExample();
            example.createCriteria().andNameEqualTo(name).andIdEqualTo(id);
            //id与名字是否匹配
            if (userMapper.selectByExample(example).size()==0){
                System.out.println("没找到/不匹配");
                return false;
            }
        }
        System.out.println("匹配");
        return true;
    }

    @Override
    public double Check(Integer[] num) throws RuntimeException {
        double sum=0.0;
        System.out.println(1);
        for (int i:num){
            System.out.println(2);
            Food food=foodMapper.selectByPrimaryKey(i);
            sum+=food.getPrice();
        }
        System.out.println(sum);
        return sum;
    }

    @Override
    public Order SelectOrder(int id) throws RuntimeException {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public void UpdateAddFoodPrice(int id, double price) throws RuntimeException {
        Order order=orderMapper.selectByPrimaryKey(id);
        order.setPrice(price);
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public boolean IsOrderExistence(int id) throws RuntimeException {
        if (orderMapper.selectByPrimaryKey(id)==null){
            return false;
        }
        else return true;
    }


}

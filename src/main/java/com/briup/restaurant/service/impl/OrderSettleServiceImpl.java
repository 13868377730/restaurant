package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.Item;
import com.briup.restaurant.bean.ItemExample;
import com.briup.restaurant.bean.Order;
import com.briup.restaurant.mapper.FoodMapper;
import com.briup.restaurant.mapper.ItemMapper;
import com.briup.restaurant.mapper.OrderMapper;
import com.briup.restaurant.service.IOrderSettleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OrderSettleServiceImpl implements IOrderSettleService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private FoodMapper foodMapper;
    @Override
    public void check(int id) throws RuntimeException {
        if ("进行中".equals(orderMapper.selectByPrimaryKey(id))){
            //确保未结算的菜品不会计入总价
            ItemExample example = new ItemExample();
            example.createCriteria().andOrderIdEqualTo(id);
            List<Item> items = itemMapper.selectByExample(example);
            for(Item item:items){
                if("未开始".equals(item.getState())){
                    //在菜单项中删除记录
                    itemMapper.deleteByPrimaryKey(item.getId());
                    //在订单表中减去对应的价钱
                    Order order = orderMapper.selectByPrimaryKey(item.getOrderId());
                    Food food=foodMapper.selectByPrimaryKey(item.getFoodId());
                    order.setPrice(order.getPrice() - food.getPrice());
                    orderMapper.updateByPrimaryKey(order);
                }
            }
            Order order = orderMapper.selectByPrimaryKey(id);
            order.setState("已核对");
            orderMapper.updateByPrimaryKey(order);
        }else{
            throw new RuntimeException("该订单已买单或已核对");
        }
    }

    @Override
    public void settle(int id) throws RuntimeException {
        if ("已核对".equals(orderMapper.selectByPrimaryKey(id))){
            Order order = orderMapper.selectByPrimaryKey(id);
            order.setState("已买单");
            orderMapper.updateByPrimaryKey(order);
        }else{
            throw new RuntimeException("该订单已买单或未核对，不可买单");
        }
    }
}

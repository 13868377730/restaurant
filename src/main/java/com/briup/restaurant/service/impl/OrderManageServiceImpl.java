package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.*;
import com.briup.restaurant.mapper.FoodMapper;
import com.briup.restaurant.mapper.ItemMapper;
import com.briup.restaurant.mapper.OrderMapper;
import com.briup.restaurant.mapper.TableMapper;
import com.briup.restaurant.mapper.ex.OrderEXMapper;
import com.briup.restaurant.service.IOrderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Service
public class OrderManageServiceImpl implements IOrderManageService {

    @Autowired
    private OrderEXMapper orderEXMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private FoodMapper foodMapper;


    @Override
    //只查询所需字段，用Map装
    //用sql语句确保最新订单在最前显示
    public List<Map<String, Object>> selectAll() throws RuntimeException {
        return orderEXMapper.selectAll();
    }


    @Override
    public Map<String, Object> showDetailById(int id) throws RuntimeException {
        if ("外卖".equals(orderMapper.selectByPrimaryKey(id).getType())){
            return orderEXMapper.showOutDetailById(id);
        }else if ("堂食".equals(orderMapper.selectByPrimaryKey(id).getType())){
            return orderEXMapper.showInDetailById(id);
        }else{
            throw new RuntimeException("订单类型未知，无法查看！");
        }
    }

    @Override
    public void updateTableById(int orderId, int tableId) throws RuntimeException {
        if (tableMapper.selectByPrimaryKey(tableId) == null){
            throw new RuntimeException("输入的餐桌号有误");
        }else if ("忙碌".equals(tableMapper.selectByPrimaryKey(tableId).getState())){
            throw new RuntimeException("该餐桌忙碌，不可换！");
        }else if ("空闲".equals(tableMapper.selectByPrimaryKey(tableId).getState())){
            //先修改换到的桌子状态
            Table table = tableMapper.selectByPrimaryKey(tableId);
            table.setState("忙碌");
            tableMapper.updateByPrimaryKey(table);

            //更改订单中的餐桌号
            Order order = orderMapper.selectByPrimaryKey(orderId);
            //修改原来的餐桌状态
            Table tablePre = tableMapper.selectByPrimaryKey(order.getTableId());
            tablePre.setState("空闲");
            tableMapper.updateByPrimaryKey(tablePre);

            order.setTableId(tableId);
            orderMapper.updateByPrimaryKey(order);
        }
    }

    //1.5
    @Override
    public void discountById(int orderId, double price) throws RuntimeException {
        if ("已核对".equals(orderMapper.selectByPrimaryKey(orderId).getState())) {
            if (price <= 0) {
                throw new RuntimeException("金额输入有误，请重新输入");
            } else if (price >= orderMapper.selectByPrimaryKey(orderId).getPrice()) {
                throw new RuntimeException("折后金额大于或等于现在金额，请重新输入");
            } else {
                Order order = orderMapper.selectByPrimaryKey(orderId);
                order.setPrice(price);
                orderMapper.updateByPrimaryKey(order);
            }
        } else {
            throw new RuntimeException("订单未核对，不可打折");
        }
    }

    @Override
    public void deleteFoodById(int orderId, int foodId) throws RuntimeException {
        ItemExample example = new ItemExample();
        example.createCriteria().andFoodIdEqualTo(foodId).andOrderIdEqualTo(orderId);
        List<Item> items = itemMapper.selectByExample(example);
        int count=0;
        for(Item item:items){
            if ("未开始".equals(item.getState())){
                //在菜单项中删除记录
                itemMapper.deleteByPrimaryKey(item.getId());
                //在订单表中减去对应的价钱
                Order order = orderMapper.selectByPrimaryKey(orderId);
                Food food=foodMapper.selectByPrimaryKey(foodId);
                order.setPrice(order.getPrice() - food.getPrice());
                orderMapper.updateByPrimaryKey(order);
            }else{
                count++;
            }
        }
        if (count != 0){
            throw new RuntimeException("该菜品有"+count+"道已完成或备餐中,其余已取消");
        }
    }

    //模糊查询必须用like，等号不显示
    @Override
    public List<Map<String, Object>> searchByCon(String key, String word) throws RuntimeException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if ("状态".equals(key)) {
            word = "%" + word + "%";
            list=orderEXMapper.selectByState(word);
            System.out.println(list);
        }else if("会员ID".equals(key)){
            word = "%" + word + "%";
            list= orderEXMapper.selectByUser(word);
        }else if("".equals(key)||key==null){
            if ("".equals(word)||word==null){
                list=orderEXMapper.selectAll();
            }else{
                word = "%" + word + "%";
                List<Map<String, Object>> list1=orderEXMapper.selectByState(word);
                List<Map<String, Object>> list2=orderEXMapper.selectByUser(word);
                System.out.println(list1);
                //集合合并
                list1.addAll(list2);
                //集合去重
                HashSet h = new HashSet(list1);
                list1.clear();
                list1.addAll(h);
                System.out.println(list1);
                list=list1;
            }
        }
        return list;
    }

    @Override
    public void deleteOrderById(int id) throws RuntimeException {
        if("进行中".equals(orderMapper.selectByPrimaryKey(id).getState())){
            //删除订单项
            ItemExample example = new ItemExample();
            example.createCriteria().andOrderIdEqualTo(id);
            List<Item> items = itemMapper.selectByExample(example);
            for (Item item:items){
                itemMapper.deleteByPrimaryKey(item.getId());
            }
            orderMapper.deleteByPrimaryKey(id);
        }else{
            throw new RuntimeException("该订单已核对或已买单，无法取消");
        }

    }

}

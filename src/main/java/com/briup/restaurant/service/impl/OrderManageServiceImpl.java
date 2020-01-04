package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Order;
import com.briup.restaurant.bean.Table;
import com.briup.restaurant.mapper.OrderMapper;
import com.briup.restaurant.mapper.TableMapper;
import com.briup.restaurant.mapper.ex.OrderEXMapper;
import com.briup.restaurant.service.IOrderManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    //只查询所需字段，用Map装
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
}

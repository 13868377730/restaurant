package com.briup.restaurant.service.impl;

import com.briup.restaurant.mapper.OrderMapper;
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
}

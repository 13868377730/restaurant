package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Day;
import com.briup.restaurant.bean.DayExample;
import com.briup.restaurant.bean.Order;
import com.briup.restaurant.bean.OrderExample;
import com.briup.restaurant.mapper.DayMapper;
import com.briup.restaurant.mapper.OrderMapper;
import com.briup.restaurant.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements IReportService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DayMapper dayMapper;

    //统计今日数据
    @Override
    public void dailySummary() throws RuntimeException {
        double totalPrice=0;
        int totalOrder=0;
        OrderExample example = new OrderExample();
        example.createCriteria().andDateEqualTo(new Date());
        List<Order> lists = orderMapper.selectByExample(example);
        for (Order order:lists){
            totalPrice =totalPrice+ order.getPrice();
            totalOrder++;
        }
        Day dayReport = new Day();
        dayReport.setDay(new Date());
        dayReport.setTotalOrder(totalOrder);
        dayReport.setTotalPrice(totalPrice);

        DayExample dayExample = new DayExample();
        dayExample.createCriteria().andDayEqualTo(new Date());
        //该日数据条不存在，直接插入
        if (dayMapper.selectByExample(dayExample) == null){
            dayMapper.insert(dayReport);
        //该日数据条存在，覆盖
        }else{
            List<Day> list = dayMapper.selectByExample(dayExample);
            for (Day day:list){
                dayReport.setId(day.getId());
                dayMapper.updateByPrimaryKey(dayReport);
            }
        }

    }
}

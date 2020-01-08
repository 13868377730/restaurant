package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.*;
import com.briup.restaurant.bean.ex.FoodSales;
import com.briup.restaurant.bean.ex.MonthlyReport;
import com.briup.restaurant.mapper.DayMapper;
import com.briup.restaurant.mapper.MonthMapper;
import com.briup.restaurant.mapper.OrderMapper;
import com.briup.restaurant.service.IFoodService;
import com.briup.restaurant.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements IReportService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DayMapper dayMapper;

    @Autowired
    private MonthMapper monthMapper;

    @Autowired
    private IFoodService iFoodService;


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
        if ( dayMapper.selectByExample(dayExample) == null ||
                dayMapper.selectByExample(dayExample).size() <= 0){
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

    //统计本月数据
    @Override
    public void monthlySummary() throws RuntimeException {
        double totalPrice=0;
        int totalOrder=0;
        // 获取前月的第一天
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDay = cale.getTime();

        //当月月份，字符串类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String month = sdf.format(firstDay);

        DayExample example = new DayExample();
        example.createCriteria().andDayBetween(firstDay,new Date());
        List<Day> lists = dayMapper.selectByExample(example);

        //计算总单数和总营业额
        for (Day day:lists){
            totalPrice =totalPrice+ day.getTotalPrice();
            totalOrder= totalOrder + day.getTotalOrder();
        }

        //查找本月的热销菜品
        List<FoodSales> hotFoods = iFoodService.selectMonth();
        //创建添加的月数据对象
        Month monthReport = new Month();
        monthReport.setMonth(month);
        monthReport.setTotalOrder(totalOrder);
        monthReport.setTotalPrice(totalPrice);
        if (hotFoods == null ||hotFoods.size() <= 0){
        }else {
            monthReport.setFoodOne(hotFoods.get(0).getFoodId());
            monthReport.setFoodTwo(hotFoods.get(1).getFoodId());
            monthReport.setFoodThree(hotFoods.get(2).getFoodId());
            monthReport.setFoodFour(hotFoods.get(3).getFoodId());
            monthReport.setFoodFive(hotFoods.get(4).getFoodId());
        }


        MonthExample monthExample = new MonthExample();
        monthExample.createCriteria().andMonthEqualTo(month);
        //该日数据条不存在，直接插入
        if ( monthMapper.selectByExample(monthExample) == null ||
                monthMapper.selectByExample(monthExample).size() <= 0){
            monthMapper.insert(monthReport);
            //该日数据条存在，覆盖
        }else{
            List<Month> list = monthMapper.selectByExample(monthExample);
            for (Month month1:list){
                monthReport.setId(month1.getId());
                monthMapper.updateByPrimaryKey(monthReport);
            }
        }
    }

    @Override
    public MonthlyReport showMonthlyReport() throws RuntimeException {
        //当月月份，字符串类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String currentMonth = sdf.format(new Date());
        //本月到现在为止的天数
        int days = Integer.parseInt( String .format("%td", new Date()) );

        //获取前12个月月份
        String[] last12Months = new String[12];
        Calendar cal = Calendar.getInstance();
        //如果当前日期大于二月份的天数28天或者29天会导致计算月份错误，会多出一个三月份，故设置一个靠前日期解决此问题
        cal.set(Calendar.DAY_OF_MONTH, 1);
        for (int i = 0; i < 12; i++) {
            //确保月份是2019-01的格式，不是2019-1
            if (cal.get(Calendar.MONTH) + 1<=9){
                last12Months[11 - i] = cal.get(Calendar.YEAR) + "-0" + (cal.get(Calendar.MONTH) + 1);
            }else {
                last12Months[11 - i] = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1);
            }
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); //逐次往前推1个月
        }

        Double[] last12MonthsPrice=new Double[12];
        for (int j = 0; j <= 11; j++) {
            MonthExample example = new MonthExample();
            example.createCriteria().andMonthEqualTo(last12Months[j]);
            if (monthMapper.selectByExample(example) == null
                    ||monthMapper.selectByExample(example).size()<=0){
                last12MonthsPrice[j] = 0.00;
            }else{
                last12MonthsPrice[j] = monthMapper.selectByExample(example).get(0).getTotalPrice();
            }
            System.out.println(last12MonthsPrice[j]);
        }

        MonthlyReport monthlyReport = new MonthlyReport();

        MonthExample monthExample = new MonthExample();
        monthExample.createCriteria().andMonthEqualTo(currentMonth);
        List<Month> month=monthMapper.selectByExample(monthExample);
        monthlyReport.setTotalPrice(month.get(0).getTotalPrice());
        monthlyReport.setTotalOrder(month.get(0).getTotalOrder());
        monthlyReport.setHotFoods(iFoodService.selectMonth());
        monthlyReport.setAveDailyOrder(month.get(0).getTotalOrder()/days);
        monthlyReport.setAveDailyPrice(month.get(0).getTotalPrice()/days);
        monthlyReport.setSalesTrend(last12MonthsPrice);

        return monthlyReport;
    }



}

package com.briup.restaurant.web.controller;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.Order;
import com.briup.restaurant.bean.Table;
import com.briup.restaurant.service.IOrderingMealService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orderingMeal")
@Api(description = "点餐管理")
public class OrderingMealController {

    @Autowired
    private IOrderingMealService orderingMealService;

    @PostMapping("/saveordering")
    @ApiOperation(value = "堂食点餐")
    //ApiImplicitParam(name = "num",value = "堂食人数",required = "query",dataType = "int",paramType = "body")
    public Message NowEat(int num, Order order, int [] ordering){
       //判断是否有桌子供应
        List<Table> list=orderingMealService.IsHasTable(num);
        System.out.println(list);
        if (list!=null&&list.size()>=1){
            //System.out.println(123);
            //桌子非空状态下，按照堂食人数选择桌子集合中的第一张桌子（确定桌子id）
            int table=list.get(0).getId();
            System.out.println(table);
            order.setTableId(table);
            order.setDate(new Date());
            //时间设定不合适
//            LocalTime time=LocalTime.now();
//            order.setTime(time);
            order.setType("堂食");
            //点单
            //点单时候，筛选食物id是否在在架
            List<Food> l= orderingMealService.IsHasFood(ordering);
            if (l.size()==0){
                orderingMealService.InsertOrder(order);
                //点单成功后，应当设置桌子状态
                orderingMealService.UpdateTable(list.get(0));
                //添加item项目
                orderingMealService.InsertItems(order,ordering);
                return MessageUtil.success("点单成功");
            }else {
                //食物下架，未能生成订单
                return MessageUtil.success(l+"食物已下架,重新筛选");
            }
        }else{
            return MessageUtil.success("没有合适人数的桌子，选择排队或这重新选择桌子人数");
        }




    }
}

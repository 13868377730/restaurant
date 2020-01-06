package com.briup.restaurant.web.controller;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.Order;
import com.briup.restaurant.bean.Table;
import com.briup.restaurant.service.IOrderingMealService;
import com.briup.restaurant.service.IUserService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/orderingMeal")
@Api(description = "点餐管理")
public class OrderingMealController {

    @Autowired
    private IOrderingMealService orderingMealService;

    @Autowired
    private IUserService userService;

    @PostMapping("/saveordering")
    @ApiOperation(value = "堂食点餐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "就餐人数",dataType = "int",paramType = "query",example = "2/4/8人桌",required = true),
            @ApiImplicitParam(name = "remark",value = "点餐备注",dataType = "String",paramType = "query",example ="备注菜品口味" ),
            @ApiImplicitParam(name = "name",value = "姓名",dataType = "String",paramType = "query",example = "凤尾虾"),
            @ApiImplicitParam(name = "user_id",value = "会员id",dataType = "int",paramType = "query",example = "会员id（选填）"),
            @ApiImplicitParam(name = "ordering",value = "点餐菜品",allowMultiple=true,dataType = "int",paramType = "query",example = "1,2,3")
    })
    public Message NowEat(Integer num, String remark,String name,Integer user_id,  Integer [] ordering) throws ParseException {
        System.out.println(ordering.length);
        Order order=new Order();
       order.setRemark(remark);
        System.out.println(user_id);
       order.setUserId(user_id);
       order.setName(name);
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
            order.setState("进行中");
            //时间设定不合适
            //Date date=new Date();
            //SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
            Date date= new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            String s = dateFormat.format(date);
//            String[] sA=s.split(":");
            //System.out.println(s);
            Date time=dateFormat.parse(s);
            //System.out.println(time);
            order.setTime(time);
            order.setType("堂食");
            //点单
            //点单时候，筛选食物id是否在在架
            List<Food> l= orderingMealService.IsHasFood(ordering);
            if (l.size()==0){
                //点单之前对会员id进行匹配
                //不具有会员id，直接进行点单
                if (order.getUserId()==null){
                    //添加订单之前计算菜品金额
                    System.out.println(123);
                    if (ordering.length!=0){
                        order.setPrice(orderingMealService.Check(ordering));
                    }else {
                        order.setPrice(0.0);
                    }
                    orderingMealService.InsertOrder(order);
                    //点单成功后，应当设置桌子状态
                    orderingMealService.UpdateTable(list.get(0));
                    //判断item是否为空
                    if (ordering.length==0){
                        return MessageUtil.success("占座成功");
                    }else {
                        //添加item项目
                        orderingMealService.InsertItems(order,ordering);
                        return MessageUtil.success("点单成功");
                    }

                }
                //具有会员id需要核对会员id与名字是否匹配
                else{
                    //会员id与姓名匹配的情况下，点单成功
                    if (orderingMealService.IsMatch(order.getUserId(),order.getName())){
                        //添加订单之前计算菜品金额
                        order.setPrice(orderingMealService.Check(ordering));
                        orderingMealService.InsertOrder(order);
                        //点单成功后，应当设置桌子状态
                        orderingMealService.UpdateTable(list.get(0));
                        //判断item状态是否为空
                            //添加item项目
                            orderingMealService.InsertItems(order,ordering);
                            return MessageUtil.success("点单成功");


                    }
                    else {
                        return MessageUtil.success("会员id与会员名字不匹配");
                    }
                }

            }else {
                //食物下架，未能生成订单
                return MessageUtil.success(l+"食物已下架,重新筛选");
            }
        }else{
            return MessageUtil.success("没有合适人数的桌子，选择排队或这重新选择桌子人数");
        }
    }

    @PostMapping("/takeaway")
    @ApiOperation(value = "外卖点餐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "姓名",dataType = "String",paramType = "query",example = "凤尾虾"),
            @ApiImplicitParam(name = "remark",value = "点餐备注",dataType = "String",paramType = "query",example = "备注菜品口味"),
            @ApiImplicitParam(name = "phone",value = "联系方式",dataType = "String",paramType = "query",example = "联系方式",required = true),
            @ApiImplicitParam(name = "address",value = "地址",dataType = "String",paramType = "query",example = "送餐地址",required = true),
            @ApiImplicitParam(name = "num",value = "点餐菜品",allowMultiple=true,dataType = "int",paramType = "query",example = "1,2,3")
    })
    public Message Takeaway(String name,String remark,String phone,String address,Integer[] num) throws ParseException {
        Order order=new Order();
        //基本信息记录
        order.setName(name);
        order.setAddress(address);
        order.setRemark(remark);
        order.setPhone(phone);
        order.setDate(new Date());
        Date date= new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String s = dateFormat.format(date);
        Date time=dateFormat.parse(s);
        order.setTime(time);
        order.setState("进行中");
        order.setType("外卖");

        //判断点单食物是否在架
        List<Food> l= orderingMealService.IsHasFood(num);
        //都在架情况
        if (l.size()==0){
            //添加订单之前计算菜品金额
            order.setPrice(orderingMealService.Check(num));
            //添加订单
            orderingMealService.InsertOrder(order);
            //判断item项目是否为空

                //添加item项目
                orderingMealService.InsertItems(order,num);
                return MessageUtil.success("点单成功");


        }
        //存在下架情况
        else {
            return MessageUtil.success(l+" 食物已下架,重新筛选");
        }
    }

    @GetMapping("/addfood")
    @ApiOperation(value = "堂食加菜")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "订单id",dataType = "int",paramType = "query",example = "1",required = true),
            @ApiImplicitParam(name = "num",value = "点餐菜品",allowMultiple=true,dataType = "int",paramType = "query",example = "1,2,3")
    })
    public Message AddFood(int id,Integer[] num){
        //订单存在
        System.out.println(123);
        if (orderingMealService.IsOrderExistence(id)){
            System.out.println(2);
            List<Food> l= orderingMealService.IsHasFood(num);
            System.out.println(l);
            System.out.println(l.size());
            if (l.size()==0){
                double price=orderingMealService.SelectOrder(id).getPrice();
                System.out.println(price);
                double p=price;
                price+=orderingMealService.Check(num);
                System.out.println(price);
                //更新订单价格
                orderingMealService.UpdateAddFoodPrice(id,price);

                Order order=orderingMealService.SelectOrder(id);
                orderingMealService.InsertItems(order,num);
                return MessageUtil.success("加菜成功,订单原有金额为："+p+"  加菜金额为："+orderingMealService.Check(num)+"  订单加菜后总金额为："+price);
            }
            //菜品下架
            else {
                return MessageUtil.success(l+" 食物已下架,重新筛选");
            }
        }
        //订单不存在
        else {
            return MessageUtil.success("兄弟你的单子不存在");
        }
    }

    @GetMapping("/settable")
    @ApiOperation("占座点单")
    @ApiImplicitParam(name = "num",value = "就餐人数",paramType = "query",dataType = "int")
    public Message SetTable(Integer num) throws ParseException {
        List<Table> l=orderingMealService.IsHasTable(num);
        if (l.size()==0)return MessageUtil.success("没有合适人数的桌子，选择排队或这重新选择桌子人数");
        else {
            //更新桌子状态信息
            orderingMealService.UpdateTable(l.get(0));
            Order order = new Order();
            order.setDate(new Date());
            Date date= new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
            String s = dateFormat.format(date);
            Date time=dateFormat.parse(s);
            order.setTime(time);
            order.setPrice(0.0);
            order.setState("进行中");
            order.setState("堂食");
            order.setTableId(l.get(0).getId());
            orderingMealService.InsertOrder(order);
            return MessageUtil.success("占座成功，桌号为："+l.get(0).getId()+",并且成功创建订单,订单号为："+order.getId());
        }
    }
}

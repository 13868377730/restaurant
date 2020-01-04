package com.briup.restaurant.web.controller;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.service.IFoodService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
@Api(description = "菜单管理")
public class FoodController {
    @Autowired
    private IFoodService ifoodService;

    @GetMapping("/addFood")
    @ApiOperation("添加菜品")
    public Message addFood(Food food){
ifoodService.addOrUpdateFood(food);
        return MessageUtil.success("添加成功");
    }

    @GetMapping("/updateFood")
    @ApiOperation("修改菜品")
    public Message updateFood(Food food){
        ifoodService.addOrUpdateFood(food);
        return MessageUtil.success("修改成功");
    }
    @GetMapping("/selectAll")
    @ApiOperation("查询所有菜品信息")
   public Message selectAll(){
        return MessageUtil.success(ifoodService.selectAll());

   }

    @GetMapping("/selectByFoodName")
    @ApiOperation("根据菜名菜品信息")
    public Message selectByFoodName(String name){
     List<Food> food=  ifoodService.selectByFoodName(name);
        return MessageUtil.success(food);

    }
}

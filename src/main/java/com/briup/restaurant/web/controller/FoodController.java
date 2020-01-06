package com.briup.restaurant.web.controller;

import com.briup.restaurant.bean.Food;
import com.briup.restaurant.bean.ex.FoodSales;
import com.briup.restaurant.service.IFoodService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/food")
@Api(description = "菜单管理")
public class FoodController {
    @Autowired
    private IFoodService ifoodService;

    @PostMapping("/addFood")
    @ApiOperation("添加菜品")
    public Message addFood(Food food){
ifoodService.addOrUpdateFood(food);
        return MessageUtil.success("添加成功");
    }
    @PostMapping("/updateFood")
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
    @GetMapping("/selectById")
    @ApiOperation("根据id菜品信息")
    public Message selectByName(int id)
    {
        return MessageUtil.success(ifoodService.selectById(id));
    }


    @GetMapping("/selectBy")
    @ApiOperation("根据菜名，菜品类型，菜品的状态查询菜品信息")
    @ApiImplicitParams({@ApiImplicitParam(name= "key1",value="菜品类型",paramType = "query" ,dataType = "String"),
@ApiImplicitParam(name= "key2",value="菜品状态",paramType = "query" ,dataType = "String")
    })
    public Message selectBy(String key1,String key2,String word){
        List<Food> foods=  ifoodService.selectBy(key1,key2,word);
        return MessageUtil.success(foods);
    }
@GetMapping("/selectByState")
@ApiOperation("查询所有在架菜品")
public Message selectByState(){

        return MessageUtil.success(ifoodService.selectByState());
}
    @GetMapping("/selectSales")
    @ApiOperation("查看菜品销量")
    @ApiImplicitParams({@ApiImplicitParam(name= "date1",value="开始日期",paramType = "query" ,dataType = "String"),
            @ApiImplicitParam(name= "date2",value="结束日期",paramType = "query" ,dataType = "String")
    })
    public Message selectSales(String date1,String date2){
        List<FoodSales> foodSales=  ifoodService.selectSales(date1, date2);
        return MessageUtil.success(foodSales);
}

}

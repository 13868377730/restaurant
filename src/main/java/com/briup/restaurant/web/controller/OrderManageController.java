package com.briup.restaurant.web.controller;

import com.briup.restaurant.service.IOrderManageService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order/manage")
@Api(description = "订单管理")
public class OrderManageController {

    @Autowired
    private IOrderManageService iOrderManageService;

    @GetMapping("/selectall")
    @ApiOperation(value = "查询所有订单")
    public Message selectAll(){
        return MessageUtil.success(iOrderManageService.selectAll());
    }

    @GetMapping("/selectdetailbyid")
    @ApiOperation(value = "查看订单详细信息")
    public Message selectDetailById(int id){
        return MessageUtil.success(iOrderManageService.showDetailById(id));
    }

    @PostMapping("/updatetablebyid")
    @ApiOperation(value = "更换餐桌")
    public Message updateTableById(int orderId, int tableId){
        iOrderManageService.updateTableById(orderId,tableId);
        return MessageUtil.success("操作成功");
    }

    @PostMapping("/discountbyid")
    @ApiOperation(value = "打折")
    public Message discountById(int orderId, double price){
        iOrderManageService.discountById(orderId,price);
        return MessageUtil.success("操作成功");
    }
    @GetMapping("/deletefoodbyid")
    @ApiOperation(value = "取消未上桌菜品")
    public Message deleteFoodById(int orderId, int foodId){
        iOrderManageService.deleteFoodById(orderId,foodId);
        return MessageUtil.success("操作成功");
    }

    @GetMapping("/search")
    @ApiOperation(value = "搜索")
    public Message searchByCon(String key, String word){
        return MessageUtil.success(iOrderManageService.searchByCon(key,word));
    }
    @GetMapping("/deleteorder")
    @ApiOperation(value = "取消订单")
    public Message deleteOrderById(int id){
        iOrderManageService.deleteOrderById(id);
        return MessageUtil.success("操作成功");
    }


}

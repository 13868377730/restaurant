package com.briup.restaurant.web.controller;

import com.briup.restaurant.service.IOrderManageService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
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
}

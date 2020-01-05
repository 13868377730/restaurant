package com.briup.restaurant.web.controller;


import com.briup.restaurant.service.IOrderManageService;
import com.briup.restaurant.service.IOrderSettleService;
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
@RequestMapping("/order/settle")
@Api(description = "订单结算")
public class OrderSettleController {
    @Autowired
    private IOrderManageService iOrderManageService;

    @Autowired
    private IOrderSettleService iOrderSettleService;

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

    @GetMapping("/deletefoodbyid")
    @ApiOperation(value = "取消未上桌菜品")
    public Message deleteFoodById(int orderId, int foodId){
        iOrderManageService.deleteFoodById(orderId,foodId);
        return MessageUtil.success("操作成功");
    }

    @PostMapping("/check")
    @ApiOperation(value = "核对订单")
    public Message check(int id){
        iOrderSettleService.check(id);
        return MessageUtil.success("操作成功");
    }

    @PostMapping("/settle")
    @ApiOperation(value = "订单结算")
    public Message settle(int id){
        iOrderSettleService.settle(id);
        return MessageUtil.success("操作成功");
    }


}

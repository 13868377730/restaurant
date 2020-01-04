package com.briup.restaurant.web.controller;

import com.briup.restaurant.bean.Waiting;
import com.briup.restaurant.service.IWaitingService;
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
@Api(description = "排号表相关操作")
@RequestMapping("waiting")
public class WaitingController {

    @Autowired
    IWaitingService iWaitingService;

    @PostMapping("insert")
    @ApiOperation("取号")
    Message insert(Waiting waiting){
        iWaitingService.insert(waiting);
        return MessageUtil.success();
    }

    @GetMapping("deleteById")
    Message deleteById(int id){
        iWaitingService.deleteById(id);
        return MessageUtil.success();
    }

    @GetMapping("deleteBatch")
    Message deleteBatch(int[] ids){
        iWaitingService.deleteBatch(ids);
        return MessageUtil.success();
    }

    @PostMapping("update")
    Message update(Waiting waiting){
        iWaitingService.update(waiting);
        return MessageUtil.success();
    }

    @GetMapping("selectById")
    Message selectById(int id){
        return MessageUtil.success(iWaitingService.selectById(id));
    }

    @GetMapping("selectAll")
    Message selectAll(){
        return MessageUtil.success(iWaitingService.selectAll());
    }
}

package com.briup.restaurant.web.controller;

import com.briup.restaurant.bean.Waiting;
import com.briup.restaurant.service.IWaitingService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
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
    @ApiOperation("增加排号")
    Message insert(Waiting waiting){
        iWaitingService.insert(waiting);
        return MessageUtil.success();
    }

    @GetMapping("deleteById")
    @ApiOperation("删除排号")
    Message deleteById(int id){
        iWaitingService.deleteById(id);
        return MessageUtil.success();
    }

    @GetMapping("deleteBatch")
    @ApiOperation("批量删除排号")
    Message deleteBatch(int[] ids){
        iWaitingService.deleteBatch(ids);
        return MessageUtil.success();
    }

    @PostMapping("update")
    @ApiOperation("更新排号")
    Message update(Waiting waiting){
        iWaitingService.update(waiting);
        return MessageUtil.success();
    }

    @GetMapping("selectById")
    @ApiOperation("查询排号")
    Message selectById(int id){
        return MessageUtil.success(iWaitingService.selectById(id));
    }

    @GetMapping("selectAll")
    @ApiOperation("查询全部排号")
    Message selectAll(){
        return MessageUtil.success(iWaitingService.selectAll());
    }

    @PostMapping("startWait")
    @ApiOperation("取号排队")
    Message startWaiting(int tableSeating, String phoneNumber){
        return MessageUtil.success(
                iWaitingService.startWait(tableSeating,phoneNumber));
    }
}

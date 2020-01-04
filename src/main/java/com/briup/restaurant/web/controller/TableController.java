package com.briup.restaurant.web.controller;


import com.briup.restaurant.bean.Table;
import com.briup.restaurant.service.ITableService;
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
@RequestMapping("/table")
@Api(description = "餐桌管理")
public class TableController {

    @Autowired
    private ITableService tableService;

    @GetMapping("/findAll")
    @ApiOperation("查询所有餐桌信息")
    public Message findAll(){
        List<Table> list = tableService.findAll();
        return MessageUtil.success(list);
    }
}

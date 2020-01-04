package com.briup.restaurant.web.controller;


import com.briup.restaurant.bean.Table;
import com.briup.restaurant.service.ITableService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/findBySth")
    @ApiOperation("条件查询")
    public Message findBySth(String key,String word){
        List<Table> list=tableService.findBySth(key, word);
        return MessageUtil.success(list);
    }

    @GetMapping("/deleteById")
    @ApiOperation("通过id删除")
    @ApiImplicitParam(name = "id",value = "id",paramType = "query",dataType = "int",required = true)
    public Message deleteById(int id){
        tableService.deleteById(id);
        return MessageUtil.success();
    }


    @GetMapping("/deleteSome")
    @ApiOperation("批量删除")
    public Message deleteSome(int[] ids){
        tableService.deleteSome(ids);
        return MessageUtil.success();
    }

    @PostMapping("/Add")
    @ApiOperation("添加餐桌")
    public Message Add(Table table){
        tableService.AddOrUpdate(table);
        return MessageUtil.success();
    }


    @PostMapping("/UpdateById")
    @ApiOperation("通过id修改餐桌")
    public Message UpdateById(Table table){
        tableService.AddOrUpdate(table);
        return MessageUtil.success();
    }

}

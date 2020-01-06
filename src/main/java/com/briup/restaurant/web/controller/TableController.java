package com.briup.restaurant.web.controller;


import com.briup.restaurant.bean.Table;
import com.briup.restaurant.service.ITableService;
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

import java.util.ArrayList;
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
    @ApiImplicitParams ({
            @ApiImplicitParam(name = "key", value = "查询条件", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "word", value = "关键字", paramType= "query", dataType = "String")
    })
    public Message findBySth(String key,String word){
        List<Table> list=tableService.findBySth(key, word);
        return MessageUtil.success(list);
    }

    @GetMapping("/deleteById")
    @ApiOperation("通过id删除")
    @ApiImplicitParam(name = "id",value = "id",paramType = "query",dataType = "int",required = true)
    public Message deleteById(int id){
        Table table= tableService.findById(id);
        if (table==null){
            return MessageUtil.success("没有此餐桌");
        }else {
            tableService.deleteById(id);
            return MessageUtil.success("删除成功");
        }

    }


    @GetMapping("/deleteSome")
    @ApiOperation("批量删除")
    public Message deleteSome(int[] ids){
        List<Integer> listY=new ArrayList<>();
        List<Integer> listN=new ArrayList<>();
        for (int id:ids){
            if(tableService.findById(id)==null){
                listN.add(id);
            }else {
                listY.add(id);
            }

        }
        tableService.deleteSome(ids);
        return MessageUtil.success(listY+"删除成功"+listN+"删除失败");
    }

    @PostMapping("/Add")
    @ApiOperation("添加餐桌")

    public Message Add(Table table){

            tableService.AddOrUpdate(table);
            return MessageUtil.success("添加成功");


    }


    @PostMapping("/UpdateById")
    @ApiOperation("通过id修改餐桌")

    public Message UpdateById(Table table){
        Table table1= tableService.findById(table.getId());
        if (table1==null){
            return MessageUtil.success("没有此餐桌");
        }else {
            tableService.AddOrUpdate(table);
            return MessageUtil.success("修改成功");
        }

    }

    @PostMapping("/changeStateById")
    @ApiOperation("通过id改变餐桌状态")
    @ApiImplicitParams ({
        @ApiImplicitParam(name = "id", value = "id", paramType = "query", dataType = "int", required = true),
        @ApiImplicitParam(name = "state", value = "状态", paramType = "query", dataType = "String", required = true)
    })
    public Message changeStateById(int id,String state){
        Table table= tableService.findById(id);
        if (table==null){
            return MessageUtil.success("没有此餐桌");
        }else {
            tableService.changeByid(id,state);
            return MessageUtil.success("修改成功");
        }

    }

}

/*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
* asd
*
*
*
*
*
*
*
*
*
*
*
*
*
* asd
*
*
*
*
*
*
*
*
*
*
*
* asd
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
* asd
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
* sad
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
* sad
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
* sad
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
*
* */
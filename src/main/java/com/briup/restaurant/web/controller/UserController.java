package com.briup.restaurant.web.controller;

import com.briup.restaurant.bean.User;
import com.briup.restaurant.service.impl.UserServiceImpl;
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
@RequestMapping("/user")
@Api(description = "会员管理")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/findall")
    @ApiOperation(value ="查询所有会员" )
    public Message FindAll(){

        //System.out.println(123);
        //System.out.println(userService.FindAll());
        return MessageUtil.success(userService.FindAll());
    }


    @GetMapping("/findbycondition")
    @ApiOperation(value = "按会员姓名查找")
    public Message FindByCondition(String name){
        //name为空,查询全部
        if ("".equals(name)||name==null){
            return MessageUtil.success(userService.FindAll());
        }
        else {
            return MessageUtil.success(userService.FindByCondition(name));
        }
    }


    @PostMapping("/saveorupdate")
    @ApiOperation(value = "添加或者修改用户")
    public Message SaveOrUpdate(User user) {
        //id为空的时候添加用户
        if (user.getId() == null || "".equals(user.getId())) {
            userService.AddUser(user);
            return MessageUtil.success("添加成功");
        }
        //id不为空，判断用户是否存在，存在则更新数据，不存在返回信息
        if (user.getId() != null && !"".equals(user.getId())) {
            //用户存在更新数据，获取之前的创建日期。
            if (userService.IsExistence(user) == true) {
                user.setCreateTime(userService.GetBeforeDay(user).getCreateTime());
                userService.Update(user);
                return MessageUtil.success("修改成功");
            } else return MessageUtil.success("修改对象不存在");
        }

        return null;
    }

    @GetMapping("/delete")
    @ApiOperation(value = "单一删除")
    public Message Delete(int id){
        userService.Delete(id);
        return MessageUtil.success("删除成功");
    }

    @GetMapping("/deletebatch")
    @ApiOperation(value = "批量删除")
    public Message DeleteBatch(int [] a){
        for (int i:a){
            userService.Delete(i);
        }
        return MessageUtil.success("删除成功");

    }

    @ApiOperation(value = "查询会员消费记录")
    @GetMapping("/findconsumption")
    public Message FindConsumption(int id){
        return MessageUtil.success(userService.FindConsumption(id));
    }


}

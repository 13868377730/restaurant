package com.briup.restaurant.web.controller;

import com.briup.restaurant.bean.Employee;
import com.briup.restaurant.bean.EmployeeExample;
import com.briup.restaurant.service.IEmployeeService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Employee")
@Api(description = "雇员管理")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @GetMapping("/insertSelective")
    @ApiOperation("/添加雇员")
    public Message insertSelective(Employee employee){
        int employee1=employeeService.insertSelective(employee);
        return MessageUtil.success(employee1);
    }
    @GetMapping("/updateByExampleSelective")
    @ApiOperation("/修改雇员")
    public  Message updateByExampleSelective
            (Employee employee, EmployeeExample example){
        int employee2=employeeService.updateByExampleSelective(employee,example);
       return  MessageUtil.success(employee2);
    }
    @GetMapping("/selectByPrimaryKey")
    @ApiOperation("/查找雇员")
    public  Message selectByPrimaryKey(int id){
        Employee employee3=employeeService.selectByPrimaryKey(id);
        return  MessageUtil.success(employee3);
    }
    @GetMapping("/deleteByPrimaryKey")
    @ApiOperation("/删除雇员")
    public Message deleteByPrimaryKey(int id){
       int employee4=employeeService.deleteByPrimaryKey(id);
       return  MessageUtil.success(employee4);

    }
}

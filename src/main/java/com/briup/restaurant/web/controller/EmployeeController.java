package com.briup.restaurant.web.controller;

import com.briup.restaurant.bean.Employee;
import com.briup.restaurant.bean.EmployeeExample;
import com.briup.restaurant.service.IEmployeeService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @GetMapping("/insertSelective")
    public Message insertSelective(Employee employee){
        int employee1=employeeService.insertSelective(employee);
        return MessageUtil.success(employee1);
    }
    @GetMapping("/updateByExampleSelective")
    public  Message updateByExampleSelective
            (Employee employee, EmployeeExample example){
        int employee2=employeeService.updateByExampleSelective(employee,example);
       return  MessageUtil.success(employee2);
    }
}

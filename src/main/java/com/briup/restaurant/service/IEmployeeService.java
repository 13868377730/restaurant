package com.briup.restaurant.service;

import com.briup.restaurant.bean.Employee;
import com.briup.restaurant.bean.EmployeeExample;
import org.apache.ibatis.annotations.Param;

public interface IEmployeeService {
    int insertSelective(Employee employee);
    int updateByExampleSelective
            (Employee employee, EmployeeExample example);
}


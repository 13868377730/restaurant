package com.briup.restaurant.service;

import com.briup.restaurant.bean.Employee;
import com.briup.restaurant.bean.EmployeeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IEmployeeService {
    int insertSelective(Employee employee);
    int updateByExampleSelective
            (Employee employee, EmployeeExample example);
    Employee selectByPrimaryKey(Integer id);
    int deleteByPrimaryKey(Integer id);
    List<Employee> selectByExample(EmployeeExample example);
    int deleteByExample(EmployeeExample example);
    List<Employee> findAll() throws  RuntimeException;
}


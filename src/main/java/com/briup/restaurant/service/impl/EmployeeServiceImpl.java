package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Employee;
import com.briup.restaurant.bean.EmployeeExample;
import com.briup.restaurant.mapper.EmployeeMapper;
import com.briup.restaurant.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public int insertSelective(Employee employee) {

        return employeeMapper.insertSelective(employee);
    }

    @Override
    public int updateByExampleSelective(Employee employee, EmployeeExample example) {

        return employeeMapper.updateByExampleSelective(employee,example);
    }

    @Override
    public Employee selectByPrimaryKey(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectByExample(EmployeeExample example) {
        return employeeMapper.selectByExample(example);
    }

    @Override
    public int deleteByExample(EmployeeExample example) {
        System.out.println(example);
        return employeeMapper.deleteByExample(example);

    }
}

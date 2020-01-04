package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Table;
import com.briup.restaurant.bean.TableExample;
import com.briup.restaurant.mapper.TableMapper;
import com.briup.restaurant.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements ITableService {
    @Autowired
    private TableMapper tableMapper;


    @Override
    public List<Table> findAll() throws RuntimeException {
        TableExample example = new TableExample();
        List<Table> list=tableMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<Table> findBySth(String key, String word) throws RuntimeException {
        return null;
    }
}

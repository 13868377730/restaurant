package com.briup.restaurant.service;

import com.briup.restaurant.bean.Table;

import java.util.List;

public interface ITableService {
    List<Table> findAll() throws  RuntimeException;
    List<Table> findBySth(String key,String word) throws  RuntimeException;
}

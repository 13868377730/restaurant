package com.briup.restaurant.service;

import com.briup.restaurant.bean.Table;

import java.util.List;

public interface ITableService {
    List<Table> findAll() throws  RuntimeException;
    List<Table> findBySth(String key,String word) throws  RuntimeException;
    void deleteById(int id) throws RuntimeException;
    void deleteSome(int[] ids) throws RuntimeException;
    void addOrUpdate(Table table) throws RuntimeException;
    void changeByid(int id,String state) throws RuntimeException;
    Table findById(int id) throws RuntimeException;
}

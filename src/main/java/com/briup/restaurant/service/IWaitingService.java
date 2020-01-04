package com.briup.restaurant.service;

import com.briup.restaurant.bean.Waiting;

import java.util.List;

public interface IWaitingService {
    void insert(Waiting waiting);
    void deleteById(int id);
    void deleteBatch(int[] ids);
    void update(Waiting waiting);
    Waiting selectById(int id);
    List<Waiting> selectAll();
}

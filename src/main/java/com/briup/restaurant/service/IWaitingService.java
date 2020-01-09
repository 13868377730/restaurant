package com.briup.restaurant.service;

import com.briup.restaurant.bean.Waiting;
import com.briup.restaurant.bean.ex.EndWait;
import com.briup.restaurant.bean.ex.StartWait;

import java.util.List;

public interface IWaitingService {
    void insert(Waiting waiting);
    void deleteById(int id);
    void deleteBatch(int[] ids);
    void update(Waiting waiting);
    Waiting selectById(int id);
    List<Waiting> selectAll();
    StartWait startWait(int seating, String phoneNumber);
    EndWait endWait(int seat);
    int intoTheSeat(int id);
    EndWait outOfDate(int id);
    void cancelWait(int id);
}

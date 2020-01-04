package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Waiting;
import com.briup.restaurant.bean.WaitingExample;
import com.briup.restaurant.bean.ex.ReturnWaiting;
import com.briup.restaurant.mapper.WaitingMapper;
import com.briup.restaurant.service.IWaitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingServiceImpl implements IWaitingService {

    @Autowired
    WaitingMapper waitingMapper;

    @Override
    public void insert(Waiting waiting) {
        waitingMapper.insert(waiting);
    }

    @Override
    public void deleteById(int id) {
        waitingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteBatch(int[] ids) {
        for(int id: ids){
            waitingMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void update(Waiting waiting) {
        waitingMapper.updateByPrimaryKey(waiting);
    }

    @Override
    public Waiting selectById(int id) {
        return waitingMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Waiting> selectAll() {
        return waitingMapper.selectByExample(new WaitingExample());
    }

    @Override
    public ReturnWaiting startWait(int seating, String phoneNumber) {
        Waiting waiting = new Waiting();
        ReturnWaiting returnWaiting = new ReturnWaiting();//返回部分数据
        waiting.setTableSeating(seating);
        waiting.setPhoneNumber(phoneNumber);
        waiting.setState("等待中");
        waitingMapper.insert(waiting);
        return returnWaiting;
    }
}

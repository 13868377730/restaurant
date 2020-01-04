package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Waiting;
import com.briup.restaurant.bean.WaitingExample;
import com.briup.restaurant.bean.ex.ReturnWaiting;
import com.briup.restaurant.mapper.WaitingMapper;
import com.briup.restaurant.mapper.ex.WaitingEXMapper;
import com.briup.restaurant.service.IWaitingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingServiceImpl implements IWaitingService {

    @Autowired
    WaitingMapper waitingMapper;
    @Autowired
    WaitingEXMapper waitingEXMapper;

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
        ReturnWaiting returnWaiting = new ReturnWaiting();
        //用来返回部分数据
        waiting.setTableSeating(seating);
        waiting.setPhoneNumber(phoneNumber);
        waiting.setState("等待中");
        waiting.setWaitingTable(100);
        //设置默认值
        waitingEXMapper.insertAndGetId(waiting);
        returnWaiting.setId(waiting.getId());
        returnWaiting.setState(waiting.getState());
        returnWaiting.setWaitTable(waiting.getWaitingTable());
        //设置要返回的数据
        return returnWaiting;
    }
}

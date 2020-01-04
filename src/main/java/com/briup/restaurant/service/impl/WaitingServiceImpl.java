package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Waiting;
import com.briup.restaurant.bean.WaitingExample;
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
}

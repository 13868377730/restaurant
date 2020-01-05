package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Table;
import com.briup.restaurant.bean.TableExample;
import com.briup.restaurant.bean.Waiting;
import com.briup.restaurant.bean.WaitingExample;
import com.briup.restaurant.bean.ex.EndWait;
import com.briup.restaurant.bean.ex.StartWait;
import com.briup.restaurant.mapper.TableMapper;
import com.briup.restaurant.mapper.WaitingMapper;
import com.briup.restaurant.mapper.ex.WaitingEXMapper;
import com.briup.restaurant.service.IOrderingMealService;
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
    @Autowired
    TableMapper tableMapper;


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
    public StartWait startWait(int seating, String phoneNumber) {
        Waiting waiting = new Waiting();
        StartWait startWait = new StartWait();
        //用来返回部分数据
        waiting.setTableSeating(seating);
        waiting.setPhoneNumber(phoneNumber);
        waiting.setState("等待中");
        waiting.setWaitingTable(waitingEXMapper.countWait()+1);
        //设置默认值
        waitingEXMapper.insertAndGetId(waiting);
        startWait.setId(waiting.getId());
        startWait.setState(waiting.getState());
        startWait.setWaitTable(waiting.getWaitingTable());
        //设置要返回的数据
        return startWait;
    }

    @Override
    public EndWait endWait(int seat){
    EndWait endWait =  waitingEXMapper.selectEnd(seat);//搜索返回信息
    waitingEXMapper.updateEnd(endWait.getId());//更新排号成功的排号信息
    waitingEXMapper.updateWait(endWait.getWaitTable());//更新该排号后的排号信息
    endWait.setState("排队完成请入座");//数据库更新了对象还没更新
    endWait.setWaitTable(0);
    return endWait;
    }

    @Override
    public int intoTheSeat(int id) {
        waitingMapper.deleteByPrimaryKey(id);
        return id;//返回id并在前端显示
    }

    @Override
    public EndWait outOfDate(int id) {
        Waiting waiting = waitingMapper.selectByPrimaryKey(id);//将排号信息放入对象
        waitingMapper.deleteByPrimaryKey(id);//删除排号记录
        EndWait endWait = endWait(waiting.getTableSeating());//根据过期排号座位数更新排号表
        endWait.setState("排号"+waiting.getId()+"过期，您的排号已完成，请入座");
        return endWait;
    }
}

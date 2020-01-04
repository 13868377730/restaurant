package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.User;
import com.briup.restaurant.bean.UserExample;
import com.briup.restaurant.mapper.UserMapper;
import com.briup.restaurant.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> FindAll() throws RuntimeException {
        UserExample example=new UserExample();
        List<User> list=userMapper.selectByExample(example);
        //System.out.println(list);
        return list;
    }

    @Override
    //已经判断非空才调用该方法
    public List<User> FindByCondition(String name) throws RuntimeException {

        //名字模糊查询
        UserExample example=new UserExample();
        example.createCriteria().andNameLike("%"+name+"%");
        List<User> list=userMapper.selectByExample(example);
        //System.out.println(name);
        //System.out.println(list);
        return list;
    }

    @Override
    public void AddUser(User user) throws RuntimeException {
        user.setCreateTime(new Date());
        userMapper.insert(user);
    }

    @Override
    public void Update(User user) throws RuntimeException {


        System.out.println(user.getCreateTime());
        userMapper.updateByPrimaryKey(user);
    }

    //判断修改信息是否存在
    @Override
    public boolean IsExistence(User user) throws RuntimeException {

        if (userMapper.selectByPrimaryKey(user.getId())!=null){
            return true;
        }else return false;
    }

    @Override
    public User GetBeforeDay(User user) throws RuntimeException {
        System.out.println(userMapper.selectByPrimaryKey(user.getId()).getCreateTime());
        return userMapper.selectByPrimaryKey(user.getId());
    }

    @Override
    public void Delete(int id) throws RuntimeException {
        userMapper.deleteByPrimaryKey(id);
    }
}

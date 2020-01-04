package com.briup.restaurant.service;

import com.briup.restaurant.bean.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IUserService {
    List<User> FindAll() throws RuntimeException;
    List<User> FindByCondition(String name) throws RuntimeException;
    void AddUser(User user) throws RuntimeException;
    void Update(User user) throws RuntimeException;
    boolean IsExistence(User user) throws RuntimeException;
    User GetBeforeDay(User user) throws RuntimeException;
    void Delete(int id) throws RuntimeException;

}

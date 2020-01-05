package com.briup.restaurant.service;

public interface IOrderSettleService {

    void check(int id) throws RuntimeException;

    void settle(int id) throws RuntimeException;
}

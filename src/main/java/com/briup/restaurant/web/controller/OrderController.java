package com.briup.restaurant.web.controller;

import com.briup.restaurant.bean.Order;
import com.briup.restaurant.bean.ex.Orderex;
import com.briup.restaurant.service.IOrderService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @GetMapping("/klj")
    public Message  selectPrice(int id){
        Orderex order=orderService.selectPrice(id);
        return MessageUtil.success(order);

    }
}

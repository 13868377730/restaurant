package com.briup.restaurant.web.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

@RestController
public class TestController {

    @GetMapping("/test")
    @ApiOperation(value = "打印当前时间")
    public void time(){
        Date date= new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String s = dateFormat.format(date);
        String[] sA=s.split(":");
        System.out.println(sA[0]);
        System.out.println(sA[1]);
        System.out.println(sA[2]);

    }
}

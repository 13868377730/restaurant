package com.briup.restaurant.web.controller;

import com.briup.restaurant.service.IReportService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@Api(description = "营业数据统计")
public class ReportController {
    @Autowired
    private IReportService iReportService;
    @GetMapping("/dailysummary")
    @ApiOperation(value = "查询所有订单")
    public Message dailySummary(){
        iReportService.dailySummary();
        return MessageUtil.success("操作成功");
    }
}

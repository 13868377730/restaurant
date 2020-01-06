package com.briup.restaurant.service;

import com.briup.restaurant.bean.ex.MonthlyReport;

public interface IReportService {
    void dailySummary() throws RuntimeException;
    void monthlySummary() throws RuntimeException;
    MonthlyReport showMonthlyReport() throws RuntimeException;
}

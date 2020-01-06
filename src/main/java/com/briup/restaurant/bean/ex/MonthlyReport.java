package com.briup.restaurant.bean.ex;

import java.util.List;

public class MonthlyReport {
    private Double totalPrice;
    private int totalOrder;
    private List<FoodSales> hotFoods;
    private Double aveDailyPrice;
    private int aveDailyOrder;
    private Double[] salesTrend;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public List<FoodSales> getHotFoods() {
        return hotFoods;
    }

    public void setHotFoods(List<FoodSales> hotFoods) {
        this.hotFoods = hotFoods;
    }

    public Double getAveDailyPrice() {
        return aveDailyPrice;
    }

    public void setAveDailyPrice(Double aveDailyPrice) {
        this.aveDailyPrice = aveDailyPrice;
    }

    public int getAveDailyOrder() {
        return aveDailyOrder;
    }

    public void setAveDailyOrder(int aveDailyOrder) {
        this.aveDailyOrder = aveDailyOrder;
    }

    public Double[] getSalesTrend() {
        return salesTrend;
    }

    public void setSalesTrend(Double[] salesTrend) {
        this.salesTrend = salesTrend;
    }

    @Override
    public String toString() {
        return "MonthlyReport{" +
                "totalPrice=" + totalPrice +
                ", totalOrder=" + totalOrder +
                ", hotFoods=" + hotFoods +
                ", aveDailyPrice=" + aveDailyPrice +
                ", aveDailyOrder=" + aveDailyOrder +
                ", salesTrend=" + salesTrend +
                '}';
    }
}

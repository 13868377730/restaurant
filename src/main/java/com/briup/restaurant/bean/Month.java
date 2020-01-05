package com.briup.restaurant.bean;

import java.io.Serializable;

public class Month implements Serializable {
    private Integer id;

    private String month;

    private Double totalPrice;

    private Integer totalOrder;

    private Integer foodOne;

    private Integer foodTwo;

    private Integer foodThree;

    private Integer foodFour;

    private Integer foodFive;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }

    public Integer getFoodOne() {
        return foodOne;
    }

    public void setFoodOne(Integer foodOne) {
        this.foodOne = foodOne;
    }

    public Integer getFoodTwo() {
        return foodTwo;
    }

    public void setFoodTwo(Integer foodTwo) {
        this.foodTwo = foodTwo;
    }

    public Integer getFoodThree() {
        return foodThree;
    }

    public void setFoodThree(Integer foodThree) {
        this.foodThree = foodThree;
    }

    public Integer getFoodFour() {
        return foodFour;
    }

    public void setFoodFour(Integer foodFour) {
        this.foodFour = foodFour;
    }

    public Integer getFoodFive() {
        return foodFive;
    }

    public void setFoodFive(Integer foodFive) {
        this.foodFive = foodFive;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", month=").append(month);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", totalOrder=").append(totalOrder);
        sb.append(", foodOne=").append(foodOne);
        sb.append(", foodTwo=").append(foodTwo);
        sb.append(", foodThree=").append(foodThree);
        sb.append(", foodFour=").append(foodFour);
        sb.append(", foodFive=").append(foodFive);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
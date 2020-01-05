package com.briup.restaurant.bean.ex;

public class EndWait {
    private int id;
    private String phoneNumber;
    private String state;
    private int waitTable;

    public int getWaitTable() {
        return waitTable;
    }

    public void setWaitTable(int waitTable) {
        this.waitTable = waitTable;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

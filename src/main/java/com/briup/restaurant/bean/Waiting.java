package com.briup.restaurant.bean;

import java.io.Serializable;

public class Waiting implements Serializable {
    private Integer id;

    private Integer tableSeating;

    private String state;

    private String phoneNumber;

    private Integer waitingTable;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTableSeating() {
        return tableSeating;
    }

    public void setTableSeating(Integer tableSeating) {
        this.tableSeating = tableSeating;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public Integer getWaitingTable() {
        return waitingTable;
    }

    public void setWaitingTable(Integer waitingTable) {
        this.waitingTable = waitingTable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", tableSeating=").append(tableSeating);
        sb.append(", state=").append(state);
        sb.append(", phoneNumber=").append(phoneNumber);
        sb.append(", waitingTable=").append(waitingTable);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
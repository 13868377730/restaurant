package com.briup.restaurant.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    @ApiModelProperty(hidden = true)
    private Integer id;


    @ApiModelProperty(hidden = true)
    private String state;

    @ApiModelProperty(hidden = true)
    private Double price;

    @ApiModelProperty(value = "订单备注")
    private String remark;

    @ApiModelProperty(hidden = true)
    private Date date;

    @ApiModelProperty(hidden = true)
    private Date time;

    @ApiModelProperty(hidden = true)
    private String type;

    @ApiModelProperty(value = "客户姓名")
    private String name;

    @ApiModelProperty(value = "联系方式")
    private String phone;

    @ApiModelProperty(value = "订单地址")
    private String address;

    @ApiModelProperty(value = "会员id")
    private Integer userId;

    @ApiModelProperty(hidden = true)
    private Integer tableId;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", state=").append(state);
        sb.append(", price=").append(price);
        sb.append(", remark=").append(remark);
        sb.append(", date=").append(date);
        sb.append(", time=").append(time);
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", address=").append(address);
        sb.append(", userId=").append(userId);
        sb.append(", tableId=").append(tableId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
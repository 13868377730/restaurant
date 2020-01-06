package com.briup.restaurant.mapper.ex;

import com.briup.restaurant.bean.ex.ItemEX;

import java.util.List;

public interface ItemEXMapper {
    List<ItemEX> findById(int id);
}

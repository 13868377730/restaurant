package com.briup.restaurant.mapper.ex;

import com.briup.restaurant.bean.Table;

import java.util.List;

public interface TableEXMapper {
    List<Table> findBySth(String key,String word);
}

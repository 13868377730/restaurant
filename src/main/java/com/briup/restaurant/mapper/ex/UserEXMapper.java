package com.briup.restaurant.mapper.ex;

import com.briup.restaurant.bean.ex.UserEX;

import java.util.List;
import java.util.Map;

public interface UserEXMapper {
    List<Map<String,Object>> FindConsumption(int id);
}

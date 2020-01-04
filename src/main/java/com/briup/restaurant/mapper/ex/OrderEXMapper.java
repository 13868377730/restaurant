package com.briup.restaurant.mapper.ex;

import java.util.List;
import java.util.Map;

public interface OrderEXMapper {
    List<Map<String,Object>> selectAll();
    Map<String,Object> showOutDetailById(int id);
    Map<String,Object> showInDetailById(int id);
}

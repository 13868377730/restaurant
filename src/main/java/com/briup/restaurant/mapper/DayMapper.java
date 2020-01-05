package com.briup.restaurant.mapper;

import com.briup.restaurant.bean.Day;
import com.briup.restaurant.bean.DayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DayMapper {
    long countByExample(DayExample example);

    int deleteByExample(DayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Day record);

    int insertSelective(Day record);

    List<Day> selectByExample(DayExample example);

    Day selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Day record, @Param("example") DayExample example);

    int updateByExample(@Param("record") Day record, @Param("example") DayExample example);

    int updateByPrimaryKeySelective(Day record);

    int updateByPrimaryKey(Day record);
}
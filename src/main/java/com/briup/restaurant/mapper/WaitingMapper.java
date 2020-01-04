package com.briup.restaurant.mapper;

import com.briup.restaurant.bean.Waiting;
import com.briup.restaurant.bean.WaitingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WaitingMapper {
    long countByExample(WaitingExample example);

    int deleteByExample(WaitingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Waiting record);

    int insertSelective(Waiting record);

    List<Waiting> selectByExample(WaitingExample example);

    Waiting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Waiting record, @Param("example") WaitingExample example);

    int updateByExample(@Param("record") Waiting record, @Param("example") WaitingExample example);

    int updateByPrimaryKeySelective(Waiting record);

    int updateByPrimaryKey(Waiting record);
}
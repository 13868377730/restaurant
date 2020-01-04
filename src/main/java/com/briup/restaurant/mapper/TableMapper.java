package com.briup.restaurant.mapper;

import com.briup.restaurant.bean.Table;
import com.briup.restaurant.bean.TableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TableMapper {
    long countByExample(TableExample example);

    int deleteByExample(TableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Table record);

    int insertSelective(Table record);

    List<Table> selectByExample(TableExample example);

    Table selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Table record, @Param("example") TableExample example);

    int updateByExample(@Param("record") Table record, @Param("example") TableExample example);

    int updateByPrimaryKeySelective(Table record);

    int updateByPrimaryKey(Table record);
}
package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.Table;
import com.briup.restaurant.bean.TableExample;
import com.briup.restaurant.mapper.TableMapper;
import com.briup.restaurant.mapper.ex.TableEXMapper;
import com.briup.restaurant.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableServiceImpl implements ITableService {
    @Autowired
    private TableMapper tableMapper;
    @Autowired
    private TableEXMapper tableEXMapper;


    @Override
    public List<Table> findAll() throws RuntimeException {
        TableExample example = new TableExample();
        List<Table> list=tableMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<Table> findBySth(String key, String word) throws RuntimeException {
        if ((key==null||"".equals(key))&&(word==null||"".equals(word))){
            //都为空
            TableExample example = new TableExample();
            List<Table> list=tableMapper.selectByExample(example);
            return list;
        }
        if ((key==null||"".equals(key))){
            //查找条件为空
            word="%"+word+"%";
            List<Table> list=tableEXMapper.findBySth(key, word);
            return list;

        }
        if (key.equals("桌型")){
            //通过桌型查找
            TableExample example = new TableExample();
            example.createCriteria().andSeatingEqualTo(Integer.valueOf(word));
            List<Table> list=tableMapper.selectByExample(example);
            return list;
        }
        if (key.equals("状态")){
            //通过状态查找
            word="%"+word+"%";
            TableExample example = new TableExample();
            example.createCriteria().andStateLike(word);
            List<Table> list=tableMapper.selectByExample(example);
            return list;
        }
        return null;
    }

    @Override
    public void deleteById(int id) throws RuntimeException {
        tableMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteSome(int[] ids) throws RuntimeException {

        for (int id:ids){
            tableMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public void AddOrUpdate(Table table) throws RuntimeException {
        if (table==null){
            throw new RuntimeException("参数为空");

        }else {
            if (table.getId()==null){
                //添加
                tableMapper.insert(table);
            }else {
                //通过id修改
                tableMapper.updateByPrimaryKey(table);
            }
        }
    }

    @Override
    public void changeByid(int id, String state) throws RuntimeException {
        Table table=new Table();
        table.setId(id);
        table.setState(state);
        tableMapper.updateByPrimaryKeySelective(table);
    }
}

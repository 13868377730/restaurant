package com.briup.restaurant.service.impl;

import com.briup.restaurant.bean.*;
import com.briup.restaurant.util.QRCodeUtil;
import com.briup.restaurant.mapper.FoodMapper;
import com.briup.restaurant.mapper.ItemMapper;
import com.briup.restaurant.mapper.OrderMapper;
import com.briup.restaurant.mapper.TableMapper;
import com.briup.restaurant.mapper.ex.OrderEXMapper;
import com.briup.restaurant.service.IOrderManageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderManageServiceImpl implements IOrderManageService {

    @Autowired
    private OrderEXMapper orderEXMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    //只查询所需字段，用Map装
    //用sql语句确保最新订单在最前显示
    public List<Map<String, Object>> selectAll() throws RuntimeException {
        return orderEXMapper.selectAll();

    }


    @Override
    public Map<String, Object> showDetailById(int id) throws RuntimeException {
        if ("外卖".equals(orderMapper.selectByPrimaryKey(id).getType())){
            return orderEXMapper.showOutDetailById(id);
        }else if ("堂食".equals(orderMapper.selectByPrimaryKey(id).getType())){
            return orderEXMapper.showInDetailById(id);
        }else{
            throw new RuntimeException("订单类型未知，无法查看！");
        }
    }

    @Override
    public void updateTableById(int orderId, int tableId) throws RuntimeException {
        if (tableMapper.selectByPrimaryKey(tableId) == null){
            throw new RuntimeException("输入的餐桌号有误");
        }else if ("忙碌".equals(tableMapper.selectByPrimaryKey(tableId).getState())){
            throw new RuntimeException("该餐桌忙碌，不可换！");
        }else if ("空闲".equals(tableMapper.selectByPrimaryKey(tableId).getState())){
            //先修改换到的桌子状态
            Table table = tableMapper.selectByPrimaryKey(tableId);
            table.setState("忙碌");
            tableMapper.updateByPrimaryKey(table);

            //更改订单中的餐桌号
            Order order = orderMapper.selectByPrimaryKey(orderId);
            //修改原来的餐桌状态
            Table tablePre = tableMapper.selectByPrimaryKey(order.getTableId());
            tablePre.setState("空闲");
            tableMapper.updateByPrimaryKey(tablePre);

            order.setTableId(tableId);
            orderMapper.updateByPrimaryKey(order);
        }
    }

    //1.5
    @Override
    public void discountById(int orderId, double discount) throws RuntimeException {
        if ("已核对".equals(orderMapper.selectByPrimaryKey(orderId).getState())) {
            if (discount <= 0 || discount >= 1) {
                throw new RuntimeException("折扣输入有误，请重新输入");
            } else {
                Order order = orderMapper.selectByPrimaryKey(orderId);
                order.setPrice(order.getPrice() * discount);
                orderMapper.updateByPrimaryKey(order);
            }
        } else {
            throw new RuntimeException("订单未核对，不可打折");
        }
    }

    @Override
    public void deleteFoodById(int orderId, int itemId) throws RuntimeException {
        if ("未开始".equals(itemMapper.selectByPrimaryKey(itemId))) {
            //在菜单项中删除记录
            itemMapper.deleteByPrimaryKey(itemId);
            //在订单表中减去对应的价钱
            Order order = orderMapper.selectByPrimaryKey(orderId);
            Food food = foodMapper.selectByPrimaryKey(itemId);
            order.setPrice(order.getPrice() - food.getPrice());
            orderMapper.updateByPrimaryKey(order);
        }else{
            throw  new RuntimeException("该订单项备餐中或已出餐");
        }
    }

    //模糊查询必须用like，等号不显示
    @Override
    public List<Map<String, Object>> searchByCon(String key, String word) throws RuntimeException {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if ("状态".equals(key)) {
            word = "%" + word + "%";
            list=orderEXMapper.selectByState(word);
            System.out.println(list);
        }else if("会员ID".equals(key)){
            word = "%" + word + "%";
            list= orderEXMapper.selectByUser(word);
        }else if("".equals(key)||key==null){
            if ("".equals(word)||word==null){
                list=orderEXMapper.selectAll();
            }else{
                word = "%" + word + "%";
                List<Map<String, Object>> list1=orderEXMapper.selectByState(word);
                List<Map<String, Object>> list2=orderEXMapper.selectByUser(word);
                System.out.println(list1);
                //集合合并
                list1.addAll(list2);
                //集合去重
                HashSet h = new HashSet(list1);
                list1.clear();
                list1.addAll(h);
                System.out.println(list1);
                list=list1;
            }
        }
        return list;
    }

    @Override
    public void deleteOrderById(int id) throws RuntimeException {
        if("进行中".equals(orderMapper.selectByPrimaryKey(id).getState())){
            int count=0;
            //删除订单项
            ItemExample example = new ItemExample();
            example.createCriteria().andOrderIdEqualTo(id);
            List<Item> items = itemMapper.selectByExample(example);
            //判断订单中所有订单项的状态
            for (Item item:items){
                if("未开始".equals(itemMapper.selectByPrimaryKey(item.getId()).getState())){
                }else {
                    count++;
                }
            }
            //有菜已完成或备餐中不可取消
            if (count > 0 ){
                throw new RuntimeException("该订单中有"+count+"道菜品已完成或备餐中");
            }else {
                for (Item item:items){
                    itemMapper.deleteByPrimaryKey(item.getId());
                }
                //订单作用桌空闲
                Table table = tableMapper.selectByPrimaryKey(orderMapper.selectByPrimaryKey(id).getTableId());
                table.setState("空闲");
                tableMapper.updateByPrimaryKey(table);
                orderMapper.deleteByPrimaryKey(id);
            }
        }else{
            throw new RuntimeException("该订单已核对或已买单，无法取消");
        }

    }

    @Override
    public void addOrUpdQRCodeById(int id) throws RuntimeException, JsonProcessingException {

        Map<String,Object> order = new HashMap<>();
        if ("外卖".equals(orderMapper.selectByPrimaryKey(id).getType())){
            order.putAll(orderEXMapper.showOutDetailById(id));
        }else if ("堂食".equals(orderMapper.selectByPrimaryKey(id).getType())){
            order.putAll(orderEXMapper.showInDetailById(id));
        }

        String str = new String();
        Iterator<Map.Entry<String, Object>> it = order.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String, Object> entry = it.next();
            if ("items".equals(entry.getKey())){
                String itemsStr = entry.getKey()+":" + entry.getValue();
                //字符串有序隔开
                String[] array=itemsStr.split(":");
                array[0] = array[0] + ":\n";
                str = str +array[0];
                String[] array1=array[1].split("},");
                for (String string:array1){
                    string = string + "}" + "\n";
                    str = str + string;
                }
            }else{
                str = str + entry.getKey()+":" + entry.getValue()+ "\n";
            }
        }
        String address = "src/main/resources/static/"+id+".jpg";
        QRCodeUtil.zxingCodeCreate(str,address);
    }
}

package com.briup.restaurant.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.briup.restaurant.bean.Table;
import com.briup.restaurant.bean.Waiting;
import com.briup.restaurant.bean.ex.EndWait;
import com.briup.restaurant.mapper.WaitingMapper;
import com.briup.restaurant.service.IOrderingMealService;
import com.briup.restaurant.service.ITableService;
import com.briup.restaurant.service.IWaitingService;
import com.briup.restaurant.util.Message;
import com.briup.restaurant.util.MessageUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.chrono.ThaiBuddhistChronology;
import java.util.Date;
import java.util.List;

@RestController
@Api(description = "排号表相关操作")
@RequestMapping("waiting")
public class WaitingController {

    @Autowired
    IWaitingService iWaitingService;
    @Autowired
    IOrderingMealService iOrderingMealService;
    @Autowired
    ITableService iTableService;
    @Autowired
    private WaitingMapper waitingMapper;

    @Autowired
    private RestTemplate restTemplate;


    //@PostMapping("insert")
    @ApiOperation("增加排号")
    Message insert(Waiting waiting){
        iWaitingService.insert(waiting);
        return MessageUtil.success();
    }


    //@GetMapping("deleteById")
    @ApiOperation("取消排号")
    Message deleteById(int id){
        iWaitingService.deleteById(id);
        return MessageUtil.success();
    }

    //@GetMapping("deleteBatch")
    @ApiOperation("批量删除排号")
    Message deleteBatch(int[] ids){
        iWaitingService.deleteBatch(ids);
        return MessageUtil.success();
    }

    //@PostMapping("update")
    @ApiOperation("更新排号")
    Message update(Waiting waiting){
        iWaitingService.update(waiting);
        return MessageUtil.success();
    }

    @GetMapping("selectById")
    @ApiOperation("用id查询排号")
    Message selectById(int id){
        return MessageUtil.success(iWaitingService.selectById(id));
    }

    @GetMapping("selectAll")
    @ApiOperation("查询全部排号")
    Message selectAll(){
        return MessageUtil.success(iWaitingService.selectAll());
    }

    @PostMapping("startWait")
    @ApiOperation("取号排队")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "seat", value = "需求座位数", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "phoneNumber", value = "联系电话", required = true, paramType = "query", dataType = "String")
    })
    Message startWait(int seat, String phoneNumber){
        List<Table> tables =
                iTableService.findBySth("桌型",seat+"");
        //判断有无该桌型
        List<Table> tables1 = iOrderingMealService.IsHasTable(seat);
        //判断有无空桌
        if (tables.size() == 0){
            return MessageUtil.success("没有该桌型");
        }else if(tables1.size() > 0){
            return MessageUtil.success("该桌型有空桌，请直接入座");
        } else{
            return MessageUtil.success(
                    iWaitingService.startWait(seat, phoneNumber));
        }
    }

    @PostMapping("endWait")
    @ApiOperation("排号完成")
    Message endWait(int seat){
        List<Table> tables = iTableService.findBySth("桌型",seat+"");
        List<Table> tables1 = iOrderingMealService.IsHasTable(seat);
        EndWait endWait = iWaitingService.endWait(seat);
        if (tables == null||tables.size() == 0){//判断有无该桌型
            return MessageUtil.success("没有该桌型");
        }else if(tables1.size() == 0){
            return MessageUtil.success("该桌型没有空桌");
        } else if(endWait == null){
            return MessageUtil.success("该桌型无需求");
        } else {

            //单发短信API
            String url = "https://open.ucpaas.com/ol/sms/sendsms";
            JSONObject jsonObject = new JSONObject();
            //基础配置，在开发平台认证后获取
            jsonObject.put("sid","ddcced57183bf8c2656ac8773d4f5f37");
            jsonObject.put("token","c15b446d94cf25df3ff660bf72ad10f5");
            jsonObject.put("appid","84d8fffe5c19486f92b165df652ebe3d");
            //模板ID，在开发平台创建模板对应的模板ID
            jsonObject.put("templateid", "528029");
            //模板对应的参数，参数之间拼接用逗号作为间隔符
            jsonObject.put("param", endWait.getId());

            Waiting waiting = waitingMapper.selectByPrimaryKey(endWait.getId());
            //要发送的手机号
            jsonObject.put("mobile", waiting.getPhoneNumber());
            //用户透传ID，随状态报告返回,可以不填写
            jsonObject.put("uid","");
            String json = JSONObject.toJSONString(jsonObject);
            //使用restTemplate进行访问远程服务
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            HttpEntity<String> httpEntity = new HttpEntity<String>(json, headers);
            String result = restTemplate.postForObject(url, httpEntity, String.class);
            System.out.println(endWait.getPhoneNumber());





            return MessageUtil.success(endWait+""+result);
        }
    }

    @PostMapping("outOfDate")
    @ApiOperation("排号过期")
    Message outOfDate(int id){
        Waiting waiting = iWaitingService.selectById(id);
        if(waiting == null){
            return MessageUtil.success("该排号不存在");
        }else if ("排队完成请入座".equals(waiting.getState())) {
            return MessageUtil.success(iWaitingService.outOfDate(id));
        }else {
            return MessageUtil.success("该排号不处于排号完成状态");
        }
    }

    @PostMapping("IntoTheSeat")
    @ApiOperation("排号完成进入座位")
    @ApiImplicitParam(name = "id", value = "排号id", paramType = "query", dataType = "int", required = true)
    Message IntoTheSeat(int id){
        Waiting waiting = iWaitingService.selectById(id);
        if(waiting == null){
            return MessageUtil.success("该排号不存在");
        }else if(!"排队完成请入座".equals(waiting.getState())){
            return MessageUtil.success("该排号不处于排号完成状态");
        }else {
            return MessageUtil.success("排号" + iWaitingService.intoTheSeat(id) +
                    "已入座,排号记录已删除");//该方法删除排号并返回id
        }
    }

    @GetMapping("cancelWait")
    @ApiOperation("取消等待离开队列")
    Message cancelWait(int id){
        iWaitingService.cancelWait(id);
        return MessageUtil.success();
    }
}

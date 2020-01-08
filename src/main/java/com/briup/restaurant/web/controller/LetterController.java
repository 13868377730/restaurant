package com.briup.restaurant.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.briup.restaurant.bean.Waiting;
import com.briup.restaurant.mapper.WaitingMapper;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/Letter")
@RestController
@Api(description = "短信通知")
public class LetterController {
    @Autowired
    private WaitingMapper waitingMapper;
    @Autowired
    private RestTemplate restTemplate;


    @ApiOperation("排号通知")
    @RequestMapping(value = "/sendsmsTest",method = RequestMethod.GET)
    public String Leter(int id){
        Waiting waiting = waitingMapper.selectByPrimaryKey(id);
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
        jsonObject.put("param", id);
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
        return result;
    }
}

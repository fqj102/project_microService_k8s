package com.eddie.user.controller;

import com.eddie.micro.user.UserInfo;
import com.eddie.user.response.Response;
import com.eddie.user.thrift.ServiceProvide;
import com.eddie.user.util.CommonUtil;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private ServiceProvide serviceProvide;

    @Autowired
    private StringRedisTemplate template;

    @PostMapping(value = "/login")
    @ResponseBody
    public Response login(@RequestParam("userName")String userName, @RequestParam("password")String password){
        UserInfo user = null;
        try {
            user = serviceProvide.getUserService().getUserByUserName(userName);
        } catch (TException e) {
            e.printStackTrace();
            return Response.USERNAME_PASSWORD_INVALID;
        }

        if (user == null){
            return Response.USERNAME_PASSWORD_INVALID;
        }

        if (!user.getPassWord().equalsIgnoreCase(CommonUtil.MD5(password))){
            return Response.USERNAME_PASSWORD_INVALID;
        }

        String token = CommonUtil.genToken(userName);

        return new Response("1000",token);
    }

    @GetMapping(value = "/test")
    @ResponseBody
    public String test(){
        if(!template.hasKey("shabao")){
            template.opsForValue().append("shabao", "我是傻宝");
            return "使用redis缓存保存数据成功";
        }else{
            template.delete("shabao");
            return "key已存在";
        }
    }
    @GetMapping(value = "/get")
    @ResponseBody
    public String getValue(){

        if(!template.hasKey("shabao")){
            return "key不存在，请先保存数据";
        }else{
            String shabao = template.opsForValue().get("shabao");//根据key获取缓存中的val
            return "获取到缓存中的数据：shabao="+shabao;
        }
    }
}

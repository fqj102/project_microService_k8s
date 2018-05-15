package com.eddie.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.eddie.micro.user.UserInfo;
import com.eddie.user.entity.PostMessage;
import com.eddie.user.entity.User;
import com.eddie.user.redis.RedisClient;
import com.eddie.user.response.Response;
import com.eddie.user.thrift.ServiceProvide;
import com.eddie.util.SignUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class UserController {

    @Autowired
    private ServiceProvide serviceProvide;

    @Autowired
    private RedisClient redisClient;

    @PostMapping(value = "/login")
    @ResponseBody
    public Response login(PostMessage message){
        UserInfo user;
        User userInfo = getJsonUserLoginInfo(message.getParams());
        try {
            user = serviceProvide.getUserService().getUserByUserName(userInfo.getUserName());
        } catch (TException e) {
            e.printStackTrace();
            return Response.USERNAME_PASSWORD_INVALID;
        }

        if (user == null || !user.getPassWord().equalsIgnoreCase(SignUtil.PasswordMD5(userInfo.getPassWord()))){
            return Response.USERNAME_PASSWORD_INVALID;
        }

        String token = SignUtil.genToken(userInfo.getUserName(),"localhost");
        BeanUtils.copyProperties(userInfo,user);

        redisClient.set(token,userInfo,3600);

        return new Response("1000",token);
    }

    @PostMapping("/sendVerifyCode")
    @ResponseBody
    public Response sendVerifyCode(@RequestParam("mobile") String mobile ,
                                   @RequestParam("email") String email){
        String message = "Verify Code is:";
        String code = randomCode("0123456789",6);
        try {
            boolean result = false;
            if (StringUtils.isNotBlank(mobile)) {
                result = serviceProvide.getMessageService().sendMobileMessage(mobile, message+code);
                redisClient.set(mobile,code);
            } else if (StringUtils.isNotBlank(email)) {
                result = serviceProvide.getMessageService().sendEmailMessage(email,message+code);
                redisClient.set(mobile,code);
            } else {
                return Response.MOBILE_OR_PHONE_REQUIRED;
            }

            if (!result){
                return Response.SEND_VERIFY_CODE_FILED;
            }
        }catch (TException e){
            return Response.exception(e);
        }
        return Response.SUCCESS;
    }

    @PostMapping(value = "/register")
    @ResponseBody
    public Response registerUser(PostMessage message){
        User user = getJsonUserLoginInfo(message.getParams());
        UserInfo userInfo = new UserInfo();
        if (StringUtils.isBlank(user.getEmail())&&StringUtils.isBlank(user.getMobile())){
            return Response.MOBILE_OR_PHONE_REQUIRED;
        }
        BeanUtils.copyProperties(user,userInfo);
        try {
            serviceProvide.getUserService().registerUser(userInfo);
        } catch (TException e) {
            return Response.exception(e);
        }

        return Response.SUCCESS;
    }

    private User getJsonUserLoginInfo(String params) {
        if (params == null || params.equals("")){
            return null;
        }
        JSONObject object = JSONObject.parseObject(params);
        String userName = object.getString("userName");
        String password = object.getString("password");

        String realName = object.getString("realName");
        String mobile = object.getString("mobile");
        String email = object.getString("email");

        User user = new User(userName,password,realName,mobile,email);
        return user;
    }

    private String randomCode(String s, int size) {
        StringBuilder result = new StringBuilder(size);

        Random random = new Random();
        for(int i=0;i<size;i++) {
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }
        return result.toString();
    }
}

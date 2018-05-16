package com.eddie.user.controller;

import com.eddie.micro.user.DTO.User;
import com.eddie.user.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private RedisClient redisClient;

    @PostMapping("/authentication")
    @ResponseBody
    public User authentication(@RequestHeader("token") String token){
        return redisClient.get(token);
    }

}

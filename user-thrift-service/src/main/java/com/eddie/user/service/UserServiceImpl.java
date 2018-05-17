package com.eddie.user.service;

import com.eddie.micro.user.UserInfo;
import com.eddie.micro.user.UserService;
import com.eddie.user.mapper.UserMapper;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService.Iface {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserInfo getUserById(int id) throws TException {
        return userMapper.getUserById(id);
    }

    @Override
    public UserInfo getTeacherById(int id) throws TException {
        return userMapper.getTeacherById(id);
    }

    @Override
    public UserInfo getUserByUserName(String userName) throws TException {
        return userMapper.getUserByName(userName);
    }

    @Override
    public void registerUser(UserInfo userInfo) throws TException {
        userMapper.registerUser(userInfo);
    }
}

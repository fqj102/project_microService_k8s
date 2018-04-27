package com.eddie.user.service;

import com.eddie.micro.user.UserInfo;
import com.eddie.micro.user.UserService;
import com.eddie.user.mapper.UserMapper;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo getUserById(int id) throws TException {
        return userMapper.getUserById(id);
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

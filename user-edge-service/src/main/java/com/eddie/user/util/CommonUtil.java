package com.eddie.user.util;

import org.springframework.util.DigestUtils;

import java.util.Date;


public class CommonUtil {

    private static final String MD5_KEY = "WHO_NAME*MD%FUCK@SPRING?";

    private static final String MODULE_NAME = "USER_EDGE_SERVICE";

    public static final String MD5(String password){
        String MIX = MD5_KEY + "/" + password;
        return DigestUtils.md5DigestAsHex(MIX.getBytes());
    }

    public static final String genToken(String userNmae){
        String date = new Date().toString();
        String TOKEN = MODULE_NAME + "/" + date + "/" + userNmae + "/" + MD5_KEY;
        return DigestUtils.md5DigestAsHex(TOKEN.getBytes());
    }
}

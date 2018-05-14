package com.eddie.util;

import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class SignUtilTest {

    @org.junit.Test
    public void passwordMD5() {
        System.out.println(SignUtil.PasswordMD5("testpassword"));
    }
}

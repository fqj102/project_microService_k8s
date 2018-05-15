package com.eddie.util;

public class SecretUtilTest {

    @org.junit.Test
    public void passwordMD5() {
        System.out.println(SecretUtil.PasswordMD5("testpassword"));
    }
}

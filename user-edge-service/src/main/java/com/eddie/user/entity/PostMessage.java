package com.eddie.user.entity;

public class PostMessage {

    private String ip;

    private String time;

    private String verifyCode;

    private String params;

    public PostMessage(String ip, String time, String sign, String params) {
        this.ip = ip;
        this.time = time;
        this.verifyCode = sign;
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String sign) {
        this.verifyCode = sign;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "ip='" + ip + '\'' +
                ", time='" + time + '\'' +
                ", sign='" + verifyCode + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}

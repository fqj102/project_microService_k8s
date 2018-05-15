package com.eddie.user.response;

import java.io.Serializable;

public class Response implements Serializable {

    private String code;

    private String message;

    public Response(){
        this.code = "0";
        this.message = "success";
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static final Response USERNAME_PASSWORD_INVALID = new Response("1001","username or password invalid");

    public static final Response MOBILE_OR_PHONE_REQUIRED = new Response("1002","mobile or phone are required");

    public static final Response SEND_VERIFY_CODE_FILED = new Response("1003","send verify code filed");

    public static final Response VERIFY_CODE_INVALID = new Response("1004","verify code invalid");

    public static final Response POST_DATA_LOST = new Response("1005","post data lost");

    public static final Response SUCCESS = new Response();

    public static final Response exception(Exception e){
        return new Response("9999",e.getMessage());
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.eddie.user.response;

import java.io.Serializable;

public class Response implements Serializable {

    private String code;

    private String message;

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static final Response USERNAME_PASSWORD_INVALID = new Response("1001","username or password not error");

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

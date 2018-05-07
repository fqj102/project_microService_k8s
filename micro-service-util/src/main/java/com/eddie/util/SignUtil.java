package com.eddie.util;

import com.alibaba.fastjson.JSONObject;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Map;

public class SignUtil {

    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private static final String SECRET_CODE = "772b9b344c1c426e1f7f2ede279764bc";

    /**
     * API访问签名
     * @param params
     * @return
     */
    public static String genSign(Map<String,String> params){
        String ts = params.get("localTime");
        String ip = params.get("localIP");

        String jsonStr = params.get("params");

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);

        String s = null;
        for (String key: jsonObject.keySet()){
            s = s.concat(jsonObject.getString(key)).concat("*");
        }

        StringBuilder builder = new StringBuilder();

        if (s != null){
            builder.append(SECRET_CODE).append("&").append(ts).append("&").append(s).append("&").append(SECRET_CODE).append("&").append(ip);
        }else {
            builder.append(SECRET_CODE).append("&").append(ts).append("&").append(SECRET_CODE).append("&").append(ip);
        }

        return MD5Encode(builder.toString());
    }

    /**
     * 密码MD5值
     * @param password
     * @return
     */
    public static final String PasswordMD5(String password){
        String MIX = SECRET_CODE + "/" + password;
        return MD5Encode(MIX);
    }

    /**
     * 通过用户名&用户IP 生成token
     * @param userName
     * @param localIP
     * @return
     */
    public static final String genToken(String userName,String localIP){
        String serverTime = new Date().toString();
        String TOKEN = localIP + "/" + serverTime + "/" + userName + "/" + SECRET_CODE;
        return MD5Encode(TOKEN);
    }

    /**
     * MD5生成工具
     * @param origin
     * @return
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes("utf-8")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    /**
     * 字符串转换16进制
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 字符转换成16禁进制
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}

package com.eddie.micro.user;

import com.eddie.micro.user.DTO.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class LoginFilter implements Filter {

    private static Cache <String,User> cache =
            CacheBuilder.newBuilder().maximumSize(10000).expireAfterWrite(3,TimeUnit.MINUTES).build();

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getParameter("token");
        if (StringUtils.isEmpty(token)){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("token")){
                    token = cookie.getValue();
                }
            }
        }
        User user = null;
        if (StringUtils.isNotEmpty(token)){
            user = cache.getIfPresent(token);
            if (user == null) {
                user = requestUserInfo(token);
            }
        }

        if (user == null){
            response.sendRedirect("http://localhost:8082/api/login");
            return;
        }

        cache.put(token,user);

        login(request, response, user);

        filterChain.doFilter(request, response);
    }

    protected abstract void login(HttpServletRequest request, HttpServletResponse response, User user);

    private User requestUserInfo(String token) {
        URL url = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        User user = null;
        try {
            url = new URL("http://localhost:8082/user/authentication");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            inputStream = connection.getInputStream();
            outputStream = connection.getOutputStream();
            String tokenBody = "token:" + token;
            outputStream.write(tokenBody.getBytes());

            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = bf.readLine())!=null){
                sb.append(line);
            }
            user = new ObjectMapper().readValue(sb.toString(),User.class);
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null && outputStream != null){
                    inputStream.close();
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return user;
    }

    public void destroy() {

    }
}

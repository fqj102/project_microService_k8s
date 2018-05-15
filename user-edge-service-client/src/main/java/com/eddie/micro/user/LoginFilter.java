package com.eddie.micro.user;

import com.eddie.micro.user.DTO.User;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {

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

        if (StringUtils.isNotEmpty(token)){
            User user = requestUserInfo(token);
        }
    }

    private User requestUserInfo(String token) {
        return null;
    }

    public void destroy() {

    }
}

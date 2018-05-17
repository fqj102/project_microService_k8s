package com.eddie.course.filter;

import com.eddie.micro.user.DTO.User;
import com.eddie.micro.user.LoginFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseFilter extends LoginFilter {
    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, User user) {
        request.setAttribute("user",user);
    }
}

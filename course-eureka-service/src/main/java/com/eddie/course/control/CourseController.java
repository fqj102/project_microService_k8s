package com.eddie.course.control;

import com.eddie.course.service.CourseServiceImpl;
import com.eddie.micro.user.DTO.User;
import com.eureka.course.DTO.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @RequestMapping("/getList")
    public List<Course> getList(HttpServletRequest request) {

        User user = (User) request.getAttribute("user");

        System.out.println(user.toString());

        return courseService.courseList();
    }


}

package com.eddie.course.control;

import com.eddie.course.service.CourseServiceImpl;
import com.eureka.course.DTO.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @RequestMapping("/getList")
    public List<Course> getList() {
        return courseService.courseList();
    }


}

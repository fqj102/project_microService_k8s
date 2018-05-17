package com.eddie.course.service;

import com.eddie.course.mapper.CourseMapper;
import com.eureka.course.DTO.Course;
import com.eureka.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    public List<Course> courseList() {

        List<Course> courses = courseMapper.listCourse();

        if (courses!=null){
            for (Course course:courses){
                Integer courseTeacher = courseMapper.getCourseTeacher(course.getId());
                if (courseTeacher != null){

                }
            }
        }

        return null;
    }

}

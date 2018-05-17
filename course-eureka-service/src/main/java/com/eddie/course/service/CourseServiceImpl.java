package com.eddie.course.service;

import com.eddie.course.mapper.CourseMapper;
import com.eddie.micro.user.DTO.Teacher;
import com.eddie.micro.user.UserInfo;
import com.eureka.course.DTO.Course;
import com.eureka.course.service.ICourseService;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ServiceProvide serviceProvide;

    public List<Course> courseList() {

        List<Course> courses = courseMapper.listCourse();

        if (courses!=null){
            for (Course course:courses){
                Integer courseTeacher = courseMapper.getCourseTeacher(course.getId());
                if (courseTeacher != null){
                    try {
                        UserInfo userInfo = serviceProvide.getUserService().getTeacherById(courseTeacher);
                        course.setTeacher(transportToTeacher(userInfo));
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return courses;
    }

    private Teacher transportToTeacher(UserInfo userInfo) {
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(userInfo,teacher);
        return teacher;
    }


}

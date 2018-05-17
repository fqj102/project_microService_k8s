package com.eddie.course.mapper;

import com.eureka.course.DTO.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {

    @Select("SELECT * FROM PE_COURSE")
    List<Course> listCourse();

    @Select("SELECT USER_ID FROM PR_TEACHER_COURSE WHERE COURSE_ID=#{COURSE_ID}")
    Integer getCourseTeacher(@Param("COURSE_ID")int  courseId);

}

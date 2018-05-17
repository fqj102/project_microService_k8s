package com.eddie.course;

import com.eddie.course.filter.CourseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class ServiceApplication {

    public static void main(String args[]){
        SpringApplication.run(ServiceApplication.class,args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        CourseFilter filter = new CourseFilter();
        filterRegistrationBean.setFilter(filter);

        List<String> urlPatterns = new ArrayList<>();
        filterRegistrationBean.setUrlPatterns(urlPatterns);

        return filterRegistrationBean;
    }
}

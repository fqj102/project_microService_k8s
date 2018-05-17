package com.eddie.eureka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CourseService {

    @Autowired
    RestTemplate restTemplate;

    public String getList() {
        return restTemplate.getForObject("http://COURSE-PROVIDER/getList",String.class);
    }
}

package com.mhc.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWord {
    @RequestMapping(path = {"/helloSpringBoot"})
    @ResponseBody
    public User HelloSpring() {
       User user = new User();
       user.setName("111");
       user.setPassword("1111");
       System.out.println("hello spring boot");
       return user;
    }
}
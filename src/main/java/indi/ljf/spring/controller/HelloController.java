package indi.ljf.spring.controller;

import indi.ljf.spring.model.entity.Student;
import indi.ljf.spring.service.ElectricService;
import indi.ljf.spring.service.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {
    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Autowired
    private ServiceImpl serviceImpl;

    @Autowired
    private ElectricService electricService;


    @GetMapping("/hello")
    public String hello() {
        System.out.println(electricService);
        return "hello,world; " + serviceImpl;
    }

    @GetMapping("/user")
    public String getUser() {
        return username + ":" + password;
    }

}

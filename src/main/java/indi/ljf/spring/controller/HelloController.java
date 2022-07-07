package indi.ljf.spring.controller;

import indi.ljf.spring.annotation.UrlVersion;
import indi.ljf.spring.common.exception.BusinessException;
import indi.ljf.spring.model.entity.Student;
import indi.ljf.spring.service.ElectricService;
import indi.ljf.spring.service.ServiceImpl;
import indi.ljf.spring.vo.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ljf
 */
@Slf4j
@RequestMapping
@RestController
@UrlVersion("v1")
public class HelloController {
    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Resource
    private ServiceImpl serviceImpl;

    @Resource
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

    @GetMapping("/boolean")
    public Boolean isExist() {
        return true;
    }


    @GetMapping("/a")
    public String error() {
        throw new BusinessException("aaa");
    }

    @GetMapping("/b")
    public ResponseData<String> normal() {
        return ResponseData.success("成功");
    }


    @PostMapping("/students")
    public String createStudent(@RequestBody Student student){
        System.out.println(student);
        return "成功";
    }
}

package indi.ljf.spring.model.entity;

import lombok.Data;

/**
 * @Date: 2022/5/17 15:20
 * @Author: ljf
 * @Description:
 */
@Data
public class Student {
    private String name;
    private Integer age;

    public Student() {
        System.out.println("student init");
    }
}

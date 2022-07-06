package indi.ljf.spring.service;

import indi.ljf.spring.model.entity.User;
import org.springframework.stereotype.Service;

/**
 * @Date: 2022/5/18 16:37
 * @Author: ljf
 * @Description:
 */
@Service
public class AdminUserService {
    public final User adminUser = new User("2021101166");

    public void login(){
        System.out.println("admin user login...");
    }

}

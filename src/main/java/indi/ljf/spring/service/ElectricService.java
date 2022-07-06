package indi.ljf.spring.service;

import indi.ljf.spring.config.AopConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Date: 2022/5/18 15:40
 * @Author: ljf
 * @Description:
 */
@Slf4j
@Service
public class ElectricService {
    @Autowired
    private AdminUserService adminUserService;

    public void charge() throws InterruptedException {
//        ElectricService electricService = ((ElectricService) AopContext.currentProxy());
//        electricService.pay();
        ElectricService electricService = ((ElectricService)AopContext.currentProxy());
        electricService.doCharge();
    }

    public void doCharge(){
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("electric charging ...");

    }

//    public void pay() throws InterruptedException {
//        adminUserService.login();
//        String payNum = adminUserService.adminUser.getPayNum();
//        log.info("user payNum : {}", payNum);
//        log.info("pay with alipay ...");
//        Thread.sleep(1000);
//    }
}

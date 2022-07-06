package indi.ljf.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * @Date: 2022/5/18 15:40
 * @Author: ljf
 * @Description:
 */
@Slf4j
@Aspect
@Service
public class AopConfig {
//    @Pointcut("execution(* indi.ljf.spring.service.ElectricService.pay()) ")
//    private void payPointCut() {
//    }

    @Around("execution(* indi.ljf.spring.service.ElectricService.doCharge())")
    public Object recordPayPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        joinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info("pay method time cost (ms): {}", end - start);
        return null;
    }

//    @Before("execution(* indi.ljf.spring.service.AdminUserService.login())")
//    public void logAdminLogin(){
//        log.info("! admin login...");
//    }
    @Before("execution(* indi.ljf.spring.service.ElectricService.charge())")
    public void checkAuthority(){
       log.info("validating user authority");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Before("execution(* indi.ljf.spring.service.ElectricService.charge())")
    public void logBeforeMethod(JoinPoint joinPoint){
        log.info("step into ->{}",joinPoint.getSignature());
    }

}

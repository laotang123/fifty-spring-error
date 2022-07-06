package indi.ljf.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class FiftySpringErrorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiftySpringErrorApplication.class, args);
//        context.close();
    }

}

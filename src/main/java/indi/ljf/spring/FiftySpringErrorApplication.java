package indi.ljf.spring;

import indi.ljf.spring.config.UrlVersionHandlerMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author liujf14
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication
public class FiftySpringErrorApplication implements WebMvcRegistrations {

    public static void main(String[] args) {
        SpringApplication.run(FiftySpringErrorApplication.class, args);
    }

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new UrlVersionHandlerMapping();
    }
}

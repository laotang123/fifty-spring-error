package indi.ljf.spring.config;

import indi.ljf.spring.annotation.UrlVersion;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author liujf14
 */
public class UrlVersionHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected boolean isHandler(Class<?> beanType) {
        return AnnotatedElementUtils.hasAnnotation(beanType, Controller.class);
    }

    @Override
    protected void registerHandlerMethod(Object handler, Method method, RequestMappingInfo mapping) {
        Class<?> controllerClass = method.getDeclaringClass();
        //类上的UrlVersion注解
        UrlVersion apiVersion = AnnotationUtils.findAnnotation(controllerClass, UrlVersion.class);
        //方法上的UrlVersion注解
        UrlVersion methodAnnotation = AnnotationUtils.findAnnotation(method, UrlVersion.class);
        //以方法上的注解优先
        if (methodAnnotation != null) {
            apiVersion = methodAnnotation;
        }

        String[] urlPatterns = apiVersion == null ? new String[0] : apiVersion.value();
        PathPatternsRequestCondition pathPatternsCondition = mapping.getPathPatternsCondition();
        //todo PathPatternsRequestCondition和PatternsRequestCondition的区别？
        String[] paths;
        if (pathPatternsCondition != null) {
            paths = mapping.getPathPatternsCondition().getPatterns().stream().map(pathPattern -> String.join("/", urlPatterns) + pathPattern.getPatternString()).toArray(String[]::new);
        } else {
            assert mapping.getPatternsCondition() != null;
            paths = mapping.getPatternsCondition().getPatterns().stream().map(pathPattern -> String.join("/", urlPatterns) +
                    Arrays.toString(pathPattern.getBytes(StandardCharsets.UTF_8))).toArray(String[]::new);
        }
        RequestMappingInfo finalMappingInfo = mapping.mutate().paths(paths).build();

        super.registerHandlerMethod(handler, method, finalMappingInfo);
    }
}

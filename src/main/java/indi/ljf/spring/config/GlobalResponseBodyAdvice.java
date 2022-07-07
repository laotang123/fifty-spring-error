package indi.ljf.spring.config;

import com.alibaba.fastjson.JSON;
import indi.ljf.spring.annotation.NoResponse;
import indi.ljf.spring.vo.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

/**
 * @author liujf
 */
@Slf4j
@RestControllerAdvice
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType() != ResponseData.class
                && AnnotationUtils.findAnnotation(Objects.requireNonNull(returnType.getMethod()), NoResponse.class) == null
                && AnnotationUtils.findAnnotation(Objects.requireNonNull(returnType.getDeclaringClass()), NoResponse.class) == null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //异常抛出 先走全局拦截 再正常写出
        //重要！！get请求默认mediaType为text/html，会走StringHttpMessageConverter 需要将包装后的response序列化为string。否则会报castException
        if (body instanceof String) {
            return JSON.toJSONString(ResponseData.success(body));
        }
        return body instanceof Exception ? ResponseData.fail("系统异常，请查看日志！") : ResponseData.success(body);
    }
}

package indi.ljf.spring.config;

import indi.ljf.spring.common.exception.BusinessException;
import indi.ljf.spring.vo.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author liujf
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理类（Http状态码200）
     *
     * @param exception 异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public <T> ResponseData<T> exceptionHandler(BusinessException exception) {
        log.error(exception.getMessage(), exception);
        return ResponseData.fail(exception.getCode(), exception.getMessage());
    }



    /**
     * 参数校验异常处理类（自定义校验）
     *
     * @param exception 异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> ResponseData<T> exceptionHandler(ConstraintViolationException exception) {
        String errorMessage = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("|"));
        log.error(exception.getMessage(), exception);
        return ResponseData.fail(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }

    /**
     * 参数校验异常处理类（框架校验）
     *
     * @param exception 异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> ResponseData<T> exceptionHandler(MethodArgumentNotValidException exception) {
        String errorMessage = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("|"));
        log.error(errorMessage, exception);
        return ResponseData.fail(HttpStatus.BAD_REQUEST.value(), errorMessage);
    }

    /**
     * 请求方法不支持
     *
     * @param exception 异常
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> ResponseData<T> exceptionHandler(HttpRequestMethodNotSupportedException exception) {
        return ResponseData.fail(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    /**
     * 请求入参格式异常
     *
     * @param exception 异常
     */
    @ExceptionHandler(value = HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public <T> ResponseData<T> exceptionHandler(HttpMessageConversionException exception) {
        return ResponseData.fail(HttpStatus.BAD_REQUEST.value(), "请求参数格式异常！");
    }

    /**
     * 全局异常处理类
     *
     * @param exception 异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public <T> ResponseData<T> exceptionHandler(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ResponseData.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "系统异常，请查看日志！");
    }

}

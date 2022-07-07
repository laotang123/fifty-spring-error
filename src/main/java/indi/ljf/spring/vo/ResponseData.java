package indi.ljf.spring.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liujf14
 */
@Data
@ApiModel(value = "统一响应报文实体")
public final class ResponseData<T> implements Serializable {

    public static final int SUCCESS = 200;
    public static final int ERROR = 500;
    private static final long serialVersionUID = -1413678375545902711L;

    private int code;
    private String message;
    private T data;

    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setData(data);
        responseData.setCode(SUCCESS);
        return responseData;
    }

    public static <T> ResponseData<T> fail(String message) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(ERROR);
        responseData.setMessage(message);
        return responseData;
    }

    public static <T> ResponseData<T> fail(int code, String message) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(code);
        responseData.setMessage(message);
        return responseData;
    }

}
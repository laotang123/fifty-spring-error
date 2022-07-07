package indi.ljf.spring;

import com.alibaba.fastjson.JSON;
import indi.ljf.spring.vo.ResponseData;

/**
 * @author liujf14
 */
public class Test02 {
    public static void main(String[] args) {
        ResponseData<String> data = ResponseData.success("a");
        System.out.println(JSON.toJSONString(data));
        System.out.println(JSON.toJSONString(ResponseData.fail("aaa")));
    }
}

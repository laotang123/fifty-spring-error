package indi.ljf.spring.model.entity;

/**
 * @Date: 2022/5/18 16:35
 * @Author: ljf
 * @Description:
 */
public class User {
    private String payNum;

    public User(String payNum){
        this.payNum = payNum;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }
}

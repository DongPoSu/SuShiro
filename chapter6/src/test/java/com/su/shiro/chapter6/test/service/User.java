package com.su.shiro.chapter6.test.service;

/**
 * @Author: suzheng
 * @Version:
 * @Package: com.su.shiro.chapter6.test.service
 * @Company: SIBU_KANGER
 * @Description:
 * @Date: 2016/05/27
 */
public class User {
    private String name;
    private String password;
    private String orderCode;


    public User() {
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public User(String name, String password, String orderCode) {
        this.name = name;
        this.password = password;
        this.orderCode = orderCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] convertToStrings() {
        return new String[]{this.name, this.password};
    }
}

package com.quincy.demo;

/**
 * @author quincy
 * @date 2018/2/27 星期二
 */
public class Good {
    private Long num;
    private String name;

    public Good(Long num, String name) {
        this.num = num;
        this.name = name;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Good{" +
                "num=" + num +
                ", name='" + name + '\'' +
                '}';
    }
}

package com.quincy.bean;

import com.quincy.enumeration.GenderEnum;

/**
 * @author quincy
 * @date 2018/3/5 星期一
 */
public class Person {
    private String name;
    private GenderEnum gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender=" + gender.toString() +
                '}';
    }
}

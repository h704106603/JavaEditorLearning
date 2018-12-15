package com.model;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

public class Car1Editor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(text == null) {
            throw new IllegalArgumentException("设置的字符串格式不正确.");
        }
        String[] infos = text.split(",");
        Car car = new Car();
        car.setBrand(infos[0]);
        car.setColor(infos[1]);
        car.setMaxSpeed(infos[2]);
        car.setWeight(Integer.parseInt(infos[3]));
        //将Car转化为属性的值
        setValue(car);
    }
}
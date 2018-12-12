package com.service;

import com.model.Boss;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class JavaBeanInfoUtil {

    // 设置bean的某个属性值
    public static <T>  void setProperty(Class<T> className,T t, String propertyName,String propertyValue) throws Exception {
        // 获取bean的某个属性的描述符
        PropertyDescriptor propDesc = new PropertyDescriptor(propertyName, className);
        // 获得用于写入属性值的方法
        Method methodSetUserName = propDesc.getWriteMethod();
        // 写入属性值
        methodSetUserName.invoke(t, propertyValue);

    }

    // 获取bean的某个属性值
    public static <T> Object getProperty(Class<T> className,T t, String propertyName) throws Exception {
        // 获取Bean的某个属性的描述符
        PropertyDescriptor proDescriptor = new PropertyDescriptor(propertyName, className);
        // 获得用于读取属性值的方法
        Method methodGetUserName = proDescriptor.getReadMethod();
        // 读取属性值
        Object propertyValue = methodGetUserName.invoke(t);
        return propertyValue;
    }

    // 通过内省设置bean的某个属性值
    public static <T> void setPropertyByIntrospector(Class<T> className,T t, String propertyName,String propertyValue) throws Exception {
        // 获取bean信息
        BeanInfo beanInfo = Introspector.getBeanInfo(Boss.class);
        // 获取bean的所有属性列表
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        // 遍历属性列表，查找指定的属性
        if (proDescrtptors != null && proDescrtptors.length > 0) {
            for (PropertyDescriptor propDesc : proDescrtptors) {
                // 找到则写入属性值
                if (propDesc.getName().equals(propertyName)) {
                    Method methodSetUserName = propDesc.getWriteMethod();
                    methodSetUserName.invoke(t, propertyValue);  // 写入属性值
                    break;
                }
            }
        }
    }

    // 通过内省获取bean的某个属性值
    public static <T> Object getPropertyByIntrospector(Class<T> className,T t,  String propertyName) throws Exception {
        Object propertyValue = null;
        BeanInfo beanInfo = Introspector.getBeanInfo(Boss.class);
        PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
        if (proDescrtptors != null && proDescrtptors.length > 0) {
            for (PropertyDescriptor propDesc : proDescrtptors) {
                if (propDesc.getName().equals(propertyName)) {
                    Method methodGetUserName = propDesc.getReadMethod();
                    propertyValue = methodGetUserName.invoke(t);
                    break;
                }
            }
        }
        return propertyValue;
    }

    public static void main(String[] args) throws Exception {

        JavaBeanInfoUtil beanInfoUtil = new JavaBeanInfoUtil();
        /*Boss boss = new Boss();

        beanInfoUtil.setProperty(Boss.class,boss,"name","孙洁");
        System.out.println(boss.getName());

        Object result = beanInfoUtil.getProperty(Boss.class,boss,"name");
        System.out.println(result);*/

        Boss boss = new Boss();
        beanInfoUtil.setPropertyByIntrospector(Boss.class,boss,"name","孙洁");
        System.out.println(boss.getName());

        Object result = beanInfoUtil.getPropertyByIntrospector(Boss.class,boss,"name");
        System.out.println(result);
    }
}
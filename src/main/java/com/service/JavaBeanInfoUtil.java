package com.service;

import com.model.Boss;

import java.beans.*;
import java.lang.reflect.Method;

public class JavaBeanInfoUtil {

    /**
     * 通过PropertyDescriptor设置属性值
     * @param className
     * @param t
     * @param propertyName
     * @param propertyValue
     * @param editorSupport
     * @param <T>
     * @throws Exception
     */
    public static <T>  void setPropertyByPropertyDescriptor(Class<T> className, T t, String propertyName, String propertyValue, PropertyEditorSupport editorSupport) throws Exception {
        // 获取bean的某个属性的描述符
        PropertyDescriptor propDesc = new PropertyDescriptor(propertyName, className);
        // 获得用于写入属性值的方法
        Method methodSetUserName = propDesc.getWriteMethod();
        // 写入属性值
        methodSetUserName.invoke(t, propertyValue);

    }

    /**
     * 通过PropertyDescriptor获得属性值
     * @param className
     * @param t
     * @param propertyName
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> Object getPropertyByPropertyDescriptor(Class<T> className,T t, String propertyName) throws Exception {
        // 获取Bean的某个属性的描述符
        PropertyDescriptor proDescriptor = new PropertyDescriptor(propertyName, className);
        // 获得用于读取属性值的方法
        Method methodGetUserName = proDescriptor.getReadMethod();
        // 读取属性值
        Object propertyValue = methodGetUserName.invoke(t);
        return propertyValue;
    }

    /**
     * 通过内省设置属性值
     * @param className
     * @param t
     * @param propertyName
     * @param propertyValue
     * @param <T>
     * @throws Exception
     */
    public static <T> void setPropertyByIntrospector(Class<T> className,T t, String propertyName,String propertyValue) throws Exception {
        // 获取bean信息
        BeanInfo beanInfo = Introspector.getBeanInfo(className);
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

    /**
     * 通过内省获得属性值
     * @param className
     * @param t
     * @param propertyName
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> Object getPropertyByIntrospector(Class<T> className,T t,  String propertyName) throws Exception {
        Object propertyValue = null;
        BeanInfo beanInfo = Introspector.getBeanInfo(className);
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

    /**
     * 获取属性描述器
     * @param className
     * @param t
     * @param propertyName
     * @param editorSupport
     * @param <T>
     * @throws Exception
     */
    public static <T,E extends PropertyEditorSupport> PropertyDescriptor getPropertyDescriptor(Class<T> className, T t, String propertyName, Class<E> editorSupport) throws Exception {
        return new PropertyDescriptor(propertyName, className);
    }

    public static void main(String[] args) throws Exception {

    }
}
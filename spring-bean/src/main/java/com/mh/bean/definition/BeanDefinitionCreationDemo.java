package com.mh.bean.definition;

import com.mh.ioc.model.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

public class BeanDefinitionCreationDemo {
    public static void main(String[] args) {
        //1.通过BeanDefinition构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        //通过属性设置
        beanDefinitionBuilder.addPropertyValue("name", "test")
                .addPropertyValue("age", 34);
        //获取实例
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        //beanDefinition 并非 Bean 终态，可以自定义修改

        //2 通过AbstractBeanDefinition 以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        //设置bean类型
        genericBeanDefinition.setBeanClass(User.class);
        // 通过MutablePropertyValues 设置
        MutablePropertyValues propertyValues = new MutablePropertyValues();
//        propertyValues.addPropertyValue("id", 1);
//        propertyValues.addPropertyValue("name", "test");
        propertyValues.add("id", "1")
                .add("name", "test");

        genericBeanDefinition.setPropertyValues(propertyValues);

    }
}

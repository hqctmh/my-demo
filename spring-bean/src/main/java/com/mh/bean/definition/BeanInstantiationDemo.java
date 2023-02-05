package com.mh.bean.definition;

import com.mh.ioc.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Bean实例化
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INFO/bean-instantiation-context.xml");
        User bean = beanFactory.getBean("user-by-static-method", User.class);
        System.out.println(bean);
        User factoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println(factoryBean);
    }


}

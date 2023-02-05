package com.mh.bean.definition;

import com.mh.ioc.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INFO/bean-definitions-context.xml");

        User testUser = beanFactory.getBean("testUser", User.class);
        System.out.println(testUser);

    }

}

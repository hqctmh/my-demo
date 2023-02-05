package com.mh.definition;

import com.mh.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanAliasDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INFO/alias-context.xml");

        User testUser = beanFactory.getBean("testUser", User.class);
        System.out.println(testUser);

    }

}

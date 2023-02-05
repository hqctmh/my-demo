package com.mh.dependency.lookup;

import com.mh.annotation.Super;
import com.mh.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 1.通过名称查找
 */
public class DependencyLookupDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INFO/Dependency-lookup-context.xml");
        lookupInRealTime(beanFactory);
        lookupInLazyTime(beanFactory);
        //根据类型查找
        lookupByType(beanFactory);
        //按照类型查找集合对象
        lookupByCollectionType(beanFactory);
        //通过注解查找
        lookupByAnnotation(beanFactory);
    }

    private static void lookupByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansWithAnnotation = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("所有标注super:"+beansWithAnnotation);

        }
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("所有的User对象:"+beansOfType);
        }
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找" + user);
    }

    private static void lookupInLazyTime(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User object = objectFactory.getObject();
        System.out.println("延迟查找" + object);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = beanFactory.getBean("user", User.class);
        System.out.println("实时查找" + user);
    }
}

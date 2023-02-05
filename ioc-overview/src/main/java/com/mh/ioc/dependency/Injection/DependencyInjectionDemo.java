package com.mh.ioc.dependency.Injection;

import com.mh.ioc.repository.UserRepository;
import com.mh.ioc.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 1.通过名称查找
 */
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INFO/Dependency-injection-context.xml");

        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        System.out.println(userRepository.getUsers());
        //依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory() );
        //依赖查找(error)
//        System.out.println(beanFactory.getBean(BeanFactory.class));
        //延迟注入
        ObjectFactory<User> userObjectFactory = userRepository.getUserObjectFactory();

        System.out.println(userObjectFactory.getObject() );

        //容器内建bean
        Environment bean = beanFactory.getBean(Environment.class);
        System.out.println(bean );

    }


}

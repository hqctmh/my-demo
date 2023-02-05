 package com.mh.ioc.container;

import com.mh.ioc.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;


 /**
  * @author mh
  */
@Configuration
 public class AnnotationApplicationContestAsIocContainerDemo {

     public static void main(String[] args) {
         AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
         //将当前类作为配置类
         applicationContext.register(AnnotationApplicationContestAsIocContainerDemo.class);
         applicationContext.refresh();
         lookupByCollectionType(applicationContext);
     }

     @Bean
     public User user(){
         User user = new User();
         user.setName("test");
         return user;
     }


     private static void lookupByCollectionType(BeanFactory beanFactory) {
         if (beanFactory instanceof ListableBeanFactory) {
             ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
             Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
             System.out.println("所有的User对象:"+beansOfType);
         }
     }

 }

package com.mh.bean.definition;

import com.mh.ioc.model.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 注解 BeanDefinition
 */
@Import(AnnotationBeanDefinitionDemo.Config.class)//3.通过 @Import来进行导入
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        //1. 通过@Bean 方式定义

        //2. 通过@Component方式
//        applicationContext.scan("com.mh");

        //3. 通过@Import来进行导入
        //注册 Configuration Class(配置类)
        applicationContext.register(AnnotationBeanDefinitionDemo.class);

        applicationContext.refresh();

        // 通过BeanDefinition注册 API实现
        // 命名 Bean 的注册方法
        registerBeanDefinition(applicationContext,"testUser2");
        //非命名注册方法
        registerBeanDefinition(applicationContext);

        Map<String, Config> configMap = applicationContext.getBeansOfType(Config.class);
        System.out.println("config 类型的所有Beans" + configMap);
        Map<String, User> userMap = applicationContext.getBeansOfType(User.class);
        System.out.println("config 类型的所有Beans" + userMap);


        //显示的关闭 Spring应用上下文
        applicationContext.close();
    }

    /**
     * 命名Bean的注册方式
     *
     * @param registry
     * @param beanName
     */
    public static void registerBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 1L).addPropertyValue("name", "test1");

        if (StringUtils.hasText(beanName)) {
            // 注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            //非命名 Bean注册方法
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }



    }

    public static void registerBeanDefinition(BeanDefinitionRegistry registry) {
        registerBeanDefinition(registry, null);
    }


    @Component//定义当前类作为Spring Bean组件
    public static class Config {
        @Bean(name = {"user", "test-user"})
        public User user() {
            User user = new User();
            user.setName("test");
            return user;
        }
    }


    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("所有的User对象:" + beansOfType);
        }
    }

}

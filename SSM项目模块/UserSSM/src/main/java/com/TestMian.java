package com;

import com.config.SpringConfig;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMian {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService bean = context.getBean(UserServiceImpl.class);
//        System.out.println(bean.getAll());
        System.out.println(bean.getById(1));

    }
}

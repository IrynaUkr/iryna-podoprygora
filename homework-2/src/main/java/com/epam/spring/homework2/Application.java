package com.epam.spring.homework2;

import com.epam.spring.homework2.config.FirstConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FirstConfig.class);
        System.out.println("bean names===============>");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        System.out.println("bean configuration ===============>");
        for (String beanname : beanDefinitionNames) {
            System.out.println(context.getBean(beanname));
        }
        context.close();
    }
}

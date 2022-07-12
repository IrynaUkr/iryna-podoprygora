package com.epam.spring.homework2;

import com.epam.spring.homework2.config.FirstConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(FirstConfig.class);
        System.out.println("beans names:");
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(name -> System.out.println(context.getBean(name)));
        System.out.println("beans configuration: ");
        Arrays.stream(context.getBeanDefinitionNames())
                .forEach(name -> System.out.println(context.getBeanDefinition(name)));
        context.close();
    }
}

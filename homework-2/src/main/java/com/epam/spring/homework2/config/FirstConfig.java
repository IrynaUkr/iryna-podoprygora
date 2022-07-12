package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import com.epam.spring.homework2.beans.BeanF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@ComponentScan("com.epam.spring.homework2")
@PropertySource("classpath:application.properties")
@Import(SecondConfig.class)
public class FirstConfig {

    @Bean(value = "beanC", initMethod = "initC", destroyMethod = "destroyC")
    @DependsOn(value = {"beanD", "beanB"})
    public BeanC beanC(@Value("${beanC.name}") final String name,
                       @Value("${beanC.value}") int value) {
        return new BeanC(name, value);
    }

    @Bean(value = "beanB", initMethod = "initB", destroyMethod = "destroyB")
    public BeanB beanB(@Value("${beanB.name}") final String name,
                       @Value("${beanB.value}") final int value) {
        return new BeanB(name, value);
    }

    @Bean(value = "beanD", initMethod = "initD", destroyMethod = "destroyD")
    public BeanD beanD(@Value("${beanD.name}") final String name,
                       @Value("${beanD.value}") final int value) {
        return new BeanD(name, value);
    }

    @Bean
    @Lazy
    public BeanF beanF() {
        return new BeanF();
    }
}

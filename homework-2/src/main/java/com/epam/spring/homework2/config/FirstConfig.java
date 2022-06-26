package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import com.epam.spring.homework2.beans.BeanF;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.epam.spring.homework2")
@PropertySource("classpath:application.properties")
@Import(SecondConfig.class)
public class FirstConfig {
    @Bean(value = "beanC", initMethod = "initC", destroyMethod = "destroyC")
    @DependsOn(value = {"beanD", "beanB"})
    public BeanC beanC() {
        return new BeanC();
    }

    @Bean(value = "beanB", initMethod = "initB", destroyMethod = "destroyB")
    public BeanB beanB() {
        return new BeanB();
    }

    @Bean(value = "beanD", initMethod = "initD", destroyMethod = "destroyD")
    public BeanD beanD() {
        return new BeanD();
    }

    @Bean
    @Lazy
    public BeanF beanF() {
        return new BeanF();
    }

}

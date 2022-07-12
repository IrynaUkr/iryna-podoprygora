package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.beans.BeanC;
import com.epam.spring.homework2.beans.BeanD;
import com.epam.spring.homework2.beans.BeanF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.Objects;

@Configuration
@ComponentScan("com.epam.spring.homework2")
@PropertySource("classpath:application.properties")
@Import(SecondConfig.class)
public class FirstConfig {
    private final Environment env;

    public FirstConfig(Environment env) {
        this.env = env;
    }

    @Bean(value = "beanC", initMethod = "initC", destroyMethod = "destroyC")
    @DependsOn(value = {"beanD", "beanB"})

    public BeanC beanC() {
        return new BeanC(env.getProperty("beanC.name"),
                Integer.parseInt(Objects.requireNonNull(env.getProperty("beanC.value"))));
    }

    @Bean(value = "beanB", initMethod = "initB", destroyMethod = "destroyB")
    public BeanB beanB() {
        return new BeanB(env.getProperty("beanB.name"),
                Integer.parseInt(Objects.requireNonNull(env.getProperty("beanB.value"))));
    }

    @Bean(value = "beanD", initMethod = "initD", destroyMethod = "destroyD")
    public BeanD beanD() {
        return new BeanD(env.getProperty("beanD.name"),
                Integer.parseInt(Objects.requireNonNull(env.getProperty("beanD.value"))));
    }

    @Bean
    @Lazy
    public BeanF beanF() {
        return new BeanF();
    }
}

package com.epam.spring.homework2.beans;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class BeanE implements MyValidator {
    private String name;
    private int value;

    public BeanE() {
    }

    @Override
    public String toString() {
        return "BeanE{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @PostConstruct
    public void postConstructE() {
        System.out.println("call method postConstructE()");
    }

    @PreDestroy
    public void preDestroyE() {
        System.out.println("call method preDestroyE()");
    }

    @Override
    public void validate() {
        if (this.name != null & this.value > 0) {
            System.out.println(this.getClass().getSimpleName() + "  is valid");
        } else {
            System.out.println(this.getClass().getSimpleName() + " is not valid");
        }
    }
}

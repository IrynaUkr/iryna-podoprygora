package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.annotation.Value;


public class BeanB implements MyValidator {
    @Value("${beanB.name}")
    private String name;
    @Value("${beanB.value}")
    private int value;

    public BeanB() {
    }

    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    private void initB() {
        System.out.println("call initB");
    }

    private void newInitMethodForBeanB() {
        System.out.println("newInitMethodForBeanB");
    }

    private void destroyB() {
        System.out.println("call destroyB");
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

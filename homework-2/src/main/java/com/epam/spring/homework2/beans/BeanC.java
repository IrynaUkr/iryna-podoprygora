package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.annotation.Value;


public class BeanC implements MyValidator {
    @Value("${beanC.name}")
    private String name;
    @Value("${beanC.value}")
    private int value;

    public BeanC() {
    }

    @Override
    public String toString() {
        return "BeanC{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    private void initC() {
        System.out.println("call initC");
    }

    private void destroyC() {
        System.out.println("call destroyC");
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

package com.epam.spring.homework1.others;

import com.epam.spring.homework1.beans.BeanA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtherBeanA {
    private final BeanA beanA;
    @Autowired
    public OtherBeanA(BeanA beanA) {
        this.beanA = beanA;
        System.out.println(this.getClass().getSimpleName() +
                " , " + beanA.getClass().getSimpleName() + " was injected through the constructor");
    }
}
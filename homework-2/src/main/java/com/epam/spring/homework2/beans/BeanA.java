package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class BeanA implements InitializingBean, DisposableBean, MyValidator {
    private String name;
    private int value;

    public BeanA() {
    }

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("call afterPropertiesSet() beanA");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("call destroy beanA");

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

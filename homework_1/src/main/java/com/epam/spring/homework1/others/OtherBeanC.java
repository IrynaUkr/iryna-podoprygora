package com.epam.spring.homework1.others;

import com.epam.spring.homework1.beans.BeanC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtherBeanC {

    @Autowired
    private BeanC beanC;

    public OtherBeanC() {
        System.out.println(this.getClass().getSimpleName()
                + " , " + beanC + " was injected through the field");
    }
}

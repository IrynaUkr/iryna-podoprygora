package com.epam.spring.homework1.pet;

import org.springframework.stereotype.Component;

@Component
public class Spider implements Animal {
    public Spider() {

    }

    @Override
    public String toString() {
        return "Spider";
    }
}

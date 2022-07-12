package com.epam.spring.homework1.pet;

import org.springframework.stereotype.Component;

@Component

public class Cat implements Animal {
    public Cat() {
    }

    @Override
    public String toString() {
        return "Cat";
    }
}

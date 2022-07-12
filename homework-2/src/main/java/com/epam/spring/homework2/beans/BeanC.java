package com.epam.spring.homework2.beans;

public class BeanC implements MyValidator {

    private final String name;

    private final int value;

    public BeanC(String name, int value) {
        this.name = name;
        this.value = value;
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

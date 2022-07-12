package com.epam.spring.homework2.beans;

public class BeanB implements MyValidator {
    private final String name;

    private final int value;

    public BeanB(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanB{" + "name='" + name + '\'' + ", value=" + value + '}';
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

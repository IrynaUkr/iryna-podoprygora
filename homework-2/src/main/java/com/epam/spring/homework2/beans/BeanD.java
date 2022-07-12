package com.epam.spring.homework2.beans;

public class BeanD implements MyValidator {

    private final String name;

    private final int value;

    public BeanD(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanD{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    private void initD() {
        System.out.println("call initD");
    }

    private void destroyD() {
        System.out.println("call destroyD");
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

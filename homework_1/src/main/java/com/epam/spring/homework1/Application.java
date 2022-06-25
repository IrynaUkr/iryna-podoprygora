package com.epam.spring.homework1;

import com.epam.spring.homework1.config.BeanConfig;
import com.epam.spring.homework1.pet.Cheetah;
import com.epam.spring.homework1.pet.PetService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        PetService petService = context.getBean(PetService.class);
        petService.printPet();
        Object cheetah = context.getBean("cheetah");
        System.out.println("by name: "+ cheetah);
        Cheetah cheetah1 = context.getBean("cheetah", Cheetah.class);
        System.out.println("by class: "+ cheetah1);
    }
}

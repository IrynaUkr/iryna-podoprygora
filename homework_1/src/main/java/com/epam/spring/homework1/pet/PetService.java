package com.epam.spring.homework1.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    private Pet pet;

    @Autowired
    public PetService(Pet pet) {
        this.pet = pet;
    }
    public void printPet(){
        pet.getAnimals().stream().forEach(System.out::println);
    }
}

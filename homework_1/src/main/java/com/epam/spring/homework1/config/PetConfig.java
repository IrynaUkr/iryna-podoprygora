package com.epam.spring.homework1.config;

import com.epam.spring.homework1.pet.Animal;
import com.epam.spring.homework1.pet.Cheetah;
import com.epam.spring.homework1.pet.Spider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.epam.spring.homework1.pet"},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = Spider.class))
public class PetConfig {

    @Bean
    @Primary
    public Animal cheetah1() {
        return new Cheetah();
    }

    @Bean
    @Qualifier("cheetah2")
    public Animal animal() {
        return new Cheetah();
    }
}

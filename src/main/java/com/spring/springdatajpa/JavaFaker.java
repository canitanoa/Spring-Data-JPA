package com.spring.springdatajpa;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JavaFaker {

    @Autowired
    StudentRepository studentRepository;

    public void generateDataFaker(){
        Faker faker = new Faker();
        for (int i=0; i<=20; i++){
            //Create info to the objects
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email =  String.format("%s_%s@company.com",firstName,lastName);
            Integer age = faker.number().numberBetween(9,90);
            //Create object Student
            Student student = new Student(firstName,lastName,email,age);
            //Save objects in DB
            studentRepository.save(student);
        }
    }
}

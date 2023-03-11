package com.spring.springdatajpa;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class JavaFaker {

    @Autowired
    StudentRepository studentRepository;

    //Genara un Student
    public void generateStudentDataFaker(){
        generateStudent();
    }

    //Genara n Students
    public void generateStudentsDataFaker(){
        Faker faker = new Faker();
        for (int i=0; i<=20; i++){
            generateStudent();
        }
    }

    private void generateStudent() {
        Student student = getStudent();
        //Save objects in DB
        studentRepository.save(student);
    }

    public static Student getStudent() {
        Faker faker = new Faker();
        //Create info to the objects
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email =  String.format("%s_%s@company.com",firstName,lastName);
        Integer age = faker.number().numberBetween(9,90);
        //Create object Student
        Student student = new Student(firstName,lastName,email,age);

        //Agrego los books a student (N - 1)
        student.addBook(new Book(faker.name().title(), LocalDateTime.now().minusDays(1)));
        student.addBook(new Book(faker.name().nameWithMiddle().toUpperCase(), LocalDateTime.now().minusDays(10)));
        student.addBook(new Book(faker.name().username(), LocalDateTime.now().minusDays(45)));



        return student;
    }


}

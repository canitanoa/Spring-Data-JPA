package com.spring.springdatajpa;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@AllArgsConstructor
public class SpringdatajpaApplication {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	JavaFaker javaFaker;

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);

	}

	@Bean
	CommandLineRunner commandLineRunner() {

		//Borro la tabla
		studentRepository.deleteAll();

		return args -> {
			//Genero los datos con java faker y los gusrado en tabla
			javaFaker.generateDataFaker();

			//Consulto los datos ordenados
			getSortingData();

			//Consulto los datos ordenados y paginados
			getPageableSortingData();


		};
	}

	private void getPageableSortingData() {
		Sort sort = Sort.by("firstName").ascending();
		PageRequest pageRequest = PageRequest.of(0,5, sort);
		Page<Student> page = studentRepository.findAll(pageRequest);
		System.out.println(page);
	}

	private void getSortingData() {
//		Sort sort = Sort.by(Sort.Direction.ASC,"firstName");
		Sort sort = Sort.by("firstName").ascending().and(Sort.by("age").descending());
		studentRepository.findAll(sort)
				.forEach(student -> System.out.println(student.getFirstName() +" "+student.getAge()));
	}

//	@Bean
//	CommandLineRunner commandLineRunner(StudentRepository studentRepository){
//		return args -> {
//			Student student1 = new Student("Adrian","Canitano","acani@gmail.com",35);
//			Student student2 = new Student("Sabrina","Dominguez","sabry@gmail.com",34);
//			Student student3 = new Student("Benicio","Canitano","beni@gmail.com",10);
//
//			studentRepository.deleteAll();
//			studentRepository.saveAll(List.of(student1,student2,student3));
//
////			System.out.println(studentRepository.count());
////
////			studentRepository
////					.findById(9L)
////					.ifPresentOrElse(System.out::println,() -> System.out.println("Student with ID 2 not found"));
////
////			List<Student> students = studentRepository.findAll();
////			students.forEach(System.out::println);
////
////
////			System.out.println("Busco el Student por Query: ");
////			studentRepository
////					.findStudentByEmail_Query("acani@gmail.com")
////					.ifPresentOrElse(System.out::println,()-> System.out.println("No encontrado"));
////
////			System.out.println("Busco el Student por Query Method: ");
////			studentRepository
////					.findStudentByEmail("acani@gmail.com")
////					.ifPresentOrElse(System.out::println,()-> System.out.println("No encontrado"));
//
////			System.out.println("Busco el Student por Query Nativo con Like: ");
////			studentRepository
////					.findStudentsByLastNameLike_QueryNativo("Do")
////					.forEach(System.out::println);
//
//			System.out.println("Busco el Student por Query Nativo con Like y multiples parametros: ");
//			studentRepository
//					.findStudentsByLastNameMutipleParams_QueryNativo("Cani", 9)
//					.forEach(System.out::println);
//
////			studentRepository
////					.findStudentsByFirstNameEqualsAndAgeEquals(
////							"Benicio",
////							10
////					).forEach(System.out::println);
////
////			studentRepository
////					.findStudentsByAgeIsGreaterThanEqual(10)
////					.forEach(System.out::println);
//
//
//			System.out.println("Borro el Student por Email");
//			System.out.println(studentRepository.deleteStudentByEmail("lalal@lala.com"));
//		};
//	}

}

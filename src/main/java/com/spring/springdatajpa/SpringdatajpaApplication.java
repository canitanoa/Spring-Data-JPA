package com.spring.springdatajpa;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
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
import java.util.Random;

@SpringBootApplication
@AllArgsConstructor
public class SpringdatajpaApplication {

	@Autowired
	StudentRepository studentRepository;
	@Autowired
	StudentIdCardRepository studentIdCardRepository;
	@Autowired
	JavaFaker javaFaker;

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);

	}

	@Bean
	CommandLineRunner commandLineRunner() {

		//Borro la tabla student
		//studentRepository.deleteAll();

		return args -> {
//			//Genero los datos con java faker y los gusrado en tabla
//			javaFaker.generateStudentsDataFaker();
//			//Consulto los datos de student ordenados
//			getStudentSortingData();
//			//Consulto los datos de student ordenados y paginados
//			getStudentPageableSortingData();

			//Creo el student
			Student student = javaFaker.getStudent();
			//Creo el studentIdCard
			StudentIdCard studentIdCard = new StudentIdCard(
					student,
					String.valueOf(RandomStringUtils.random(15, false, true))
			);
			//Agrego el studentIdCard a student
			student.setStudentIdCard(studentIdCard);

//			//Enrolo el estudiante a un curso
//			student.enrolToCourse(new Course("Maths","Exactas"));
//			student.enrolToCourse(new Course("Computer Science","IT"));

			student.setEnrolment(new Enrolment(
					new EnrolmentId(1L,1L),
					student,
					new Course("Maths","Exactas"))
			);

			student.setEnrolment(new Enrolment(
					new EnrolmentId(1L,2L),
					student,
					new Course("Computer Science","IT"))
			);

			//Guardo student en DB
			studentRepository.save(student);



			//Guardo el studentIdCard en DB (este tambien guarda al student)
//			studentIdCardRepository.save(studentIdCard);

			//Busco el student y obtengo los books asociados
			studentRepository.findById(1L)
							.ifPresent(s -> {
								System.out.println("Busca los libros...");
								List<Book> books = s.getBooks();
								books.forEach(book -> {
									System.out.println(
											s.getFirstName() + " de " + book.getBook_name()
									);
								});
							});


			//studentIdCardRepository.save(studentIdCard);

			studentIdCardRepository.findAll()
					.forEach(studentIdCard1 -> System.out.println(
							studentIdCard1.getStudent().getId() +" "+
							studentIdCard1.getStudent().getFirstName() +" "+
							studentIdCard1.getStudent().getEmail() +" "+
							studentIdCard1.getCard_number()
						)
					);

//			studentRepository.findById(242L)
//					.ifPresentOrElse(
//						System.out::println,() ->
//						System.out.println("Student not found")
//			);
//
//			studentIdCardRepository.findById(9L)
//					.ifPresentOrElse(
//							System.out::println,() ->
//							System.out.println("StudentIdCard not found")
//					);

			//Borro el registro con relacion bidireccional
			//studentRepository.deleteById(242L);

		};
	}

	private void getStudentPageableSortingData() {
		Sort sort = Sort.by("firstName").ascending();
		PageRequest pageRequest = PageRequest.of(0,5, sort);
		Page<Student> page = studentRepository.findAll(pageRequest);
		System.out.println(page);
	}

	private void getStudentSortingData() {
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

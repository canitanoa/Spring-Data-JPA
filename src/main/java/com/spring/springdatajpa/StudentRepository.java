package com.spring.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {

    /*
    Metodos definidos con QueryMethods
    (La query se genera automaticamete con la definicion del metodo)
    */
    Optional<Student> findStudentByEmail(String email);
    List<Student> findStudentsByFirstNameEqualsAndAgeEquals(String firstNAme, Integer age);
    List<Student> findStudentsByAgeIsGreaterThanEqual(Integer age);

    /*
    Metodo definido con JPQL: Java Persistence Query Language
    (Para hacer consultas entre Entities de DB mapeadas en el proyecto)
    (Este formato asegura la compatibilidad de queries entre DBs)
    */
    @Query("select s from Student s where s.email = ?1")
    Optional<Student> findStudentByEmail_Query(String email);

    /*
    Metodo definido con SQL Nativo
    (Este formato NO asegura la compatibilidad de queries entre DBs)
    * */
    @Query(value = "select * from student where last_name like concat('%',?1,'%')", nativeQuery = true)
    List<Student> findStudentsByLastNameLike_QueryNativo(String lastName);

    /*
    Metodo definido con SQL Nativo
    con multiples parametros,
    este formato permite que sean reutilizables en queries muy largas
    (Este formato NO asegura la compatibilidad de queries entre DBs)
    * */
    @Query(
            value =
                    "select * from student " +
                    "where last_name like concat('%',:last_name,'%') " +
                    "and age > :age"
            , nativeQuery = true)
    List<Student> findStudentsByLastNameMutipleParams_QueryNativo(@Param("last_name") String lastName, @Param("age") Integer age);

    @Transactional
    @Modifying //Indica a Spring que no se pamea con una Entity y que realiza una modif en la DB
    @Query("Delete from Student u Where u.email = :email")
    int deleteStudentByEmail(@Param("email") String email);


}

package com.spring.springdatajpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name="Book")
@Table(name = "book")
public class Book {

    @Id
    @SequenceGenerator(name = "book_sequence",sequenceName = "book_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "book_sequence")
    @Column(name = "id",updatable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name = "student_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_book_fk"
            )
    )
    private Student student;

    @Column(name = "book_name",nullable = false,columnDefinition = "TEXT")
    private String book_name;

    @Column(name = "create_at",nullable = false,columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDateTime create_at;

    public Book() {
    }

    public Book(String book_name, LocalDateTime create_at) {
        this.book_name = book_name;
        this.create_at = create_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDateTime create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", student=" + student +
                ", book_name='" + book_name + '\'' +
                ", create_at='" + create_at + '\'' +
                '}';
    }


}

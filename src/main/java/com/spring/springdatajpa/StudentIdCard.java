package com.spring.springdatajpa;

import javax.persistence.*;

@Entity(name="StudentIdCard")
@Table(
        name = "student_id_card",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_id_card_card_number_uk", columnNames = "card_number")
        })
public class StudentIdCard {

    @Id
    @SequenceGenerator(name = "student_id_card_sequence",sequenceName = "student_id_card_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "student_id_card_sequence")
    @Column(name = "id",updatable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_student_id_card_fk"
            )
    )
    private Student student;

    @Column(name = "card_number",nullable = false,length = 15)
    private String card_number;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentIdCard() {
    }

    public StudentIdCard(String card_number) {
        this.card_number = card_number;
    }

    public StudentIdCard(Student student, String card_number) {
        this.student = student;
        this.card_number = card_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    @Override
    public String toString() {
        return "StudentIdCard{" +
                "id=" + id +
                ", student=" + student +
                ", card_number='" + card_number + '\'' +
                '}';
    }
}

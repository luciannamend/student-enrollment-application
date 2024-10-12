package com.lucianna.mendonca.studentenrollmentapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_no")
    private Long applicationNo;

    // Foreign key "one student can have multiple enrollments"
    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    // Foreign key "one program can have multiple enrollments"
    @NotNull
    @ManyToOne
    @JoinColumn(name = "program_code")
    private Program program;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "amount_paid")
    private BigDecimal amountPaid;

    // Getters and Setters (omitted for brevity)
}

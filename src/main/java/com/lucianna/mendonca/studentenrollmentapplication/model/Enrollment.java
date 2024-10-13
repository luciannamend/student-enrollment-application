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

    // Constructors
    public Enrollment() {
    }

    public Enrollment(Long applicationNo, Student student, Program program, LocalDate startDate, BigDecimal amountPaid) {
        this.applicationNo = applicationNo;
        this.student = student;
        this.program = program;
        this.startDate = startDate;
        this.amountPaid = amountPaid;
    }

    // Getters and Setters
    public Long getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(Long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public @NotNull Student getStudent() {
        return student;
    }

    public void setStudent(@NotNull Student student) {
        this.student = student;
    }

    public @NotNull Program getProgram() {
        return program;
    }

    public void setProgram(@NotNull Program program) {
        this.program = program;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
}

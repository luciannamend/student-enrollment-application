package com.lucianna.mendonca.studentenrollmentapplication.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "program")
public class Program {

    @Id
    @Column(name = "program_code")
    private Integer programCode;

    @Column(name = "program_name" )
    private String programName;

    @Column(name = "duration" )
    private String duration;

    @Column(name = "fee" )
    private BigDecimal fee;

    @Column(name = "professor" )
    private String professor;

    // One program can have multiple enrollments
    @OneToMany(mappedBy = "program", fetch = FetchType.LAZY)
    private Set<Enrollment> enrollments;

    // Constructors
    public Program() {
    }

    public Program(int programCode, String programName, String duration, BigDecimal fee,
                   String professor, Set<Enrollment> enrollments) {
        this.programCode = programCode;
        this.programName = programName;
        this.duration = duration;
        this.fee = fee;
        this.professor = professor;
        this.enrollments = enrollments;
    }

    // Getters and Setters
    public Integer getProgramCode() {
        return programCode;
    }

    public void setProgramCode(Integer programCode) {
        this.programCode = programCode;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}

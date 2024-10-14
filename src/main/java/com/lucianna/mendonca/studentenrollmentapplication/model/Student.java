package com.lucianna.mendonca.studentenrollmentapplication.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name" )
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Nullable
    @Column(name = "technical_skills")
    private String technicalSkills;

    // One student can have multiple enrollments
    @OneToMany()
    private List<Enrollment> enrollments;


    //Constructors
    public Student() {
    }

    public Student(Long studentId, String userName, String password, String firstName, String lastName, String address,
                   String city, String postalCode, String technicalSkills) {
        this.studentId = studentId;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.technicalSkills = technicalSkills;
    }

    // Getters and Setters

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Nullable
    public String getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(@Nullable String technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}

package com.lucianna.mendonca.studentenrollmentapplication.repository;

import com.lucianna.mendonca.studentenrollmentapplication.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUserName(String userName);

    Student findStudentByStudentId(Long studentId);
}

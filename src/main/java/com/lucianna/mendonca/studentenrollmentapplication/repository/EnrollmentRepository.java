package com.lucianna.mendonca.studentenrollmentapplication.repository;

import com.lucianna.mendonca.studentenrollmentapplication.model.Enrollment;
import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import com.lucianna.mendonca.studentenrollmentapplication.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudent(Student student);
    List<Enrollment> findByProgram(Program program);

}

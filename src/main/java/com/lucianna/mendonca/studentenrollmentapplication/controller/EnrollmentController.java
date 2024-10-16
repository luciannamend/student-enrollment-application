package com.lucianna.mendonca.studentenrollmentapplication.controller;

import com.lucianna.mendonca.studentenrollmentapplication.model.Enrollment;
import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import com.lucianna.mendonca.studentenrollmentapplication.model.Student;
import com.lucianna.mendonca.studentenrollmentapplication.repository.EnrollmentRepository;
import com.lucianna.mendonca.studentenrollmentapplication.repository.ProgramRepository;
import com.lucianna.mendonca.studentenrollmentapplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.time.LocalDate;

@Controller()
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Save enrollment in db
    @PostMapping("/enroll")
    public String enrollInProgram(@ModelAttribute ("student") Student student,
                                  @ModelAttribute("program") Program program) {
        // Create a new enrollment record
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setProgram(program);
        enrollment.setStartDate(LocalDate.of(2025, 01, 16));
        enrollment.setAmountPaid(program.getFee());

        // Save the enrollment in the database
        enrollmentRepository.save(enrollment);

        // Return enrollment confirmation page
        return "enrollmentcompleted";
    }
}


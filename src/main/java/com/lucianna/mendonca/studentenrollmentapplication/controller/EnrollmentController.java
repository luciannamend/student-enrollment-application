package com.lucianna.mendonca.studentenrollmentapplication.controller;

import com.lucianna.mendonca.studentenrollmentapplication.model.Enrollment;
import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import com.lucianna.mendonca.studentenrollmentapplication.model.Student;
import com.lucianna.mendonca.studentenrollmentapplication.repository.EnrollmentRepository;
import com.lucianna.mendonca.studentenrollmentapplication.repository.ProgramRepository;
import com.lucianna.mendonca.studentenrollmentapplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller()
public class EnrollmentController {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;


    @PostMapping("/enroll")
    public String enrollInProgram(@ModelAttribute ("student") Student student,
                                  @ModelAttribute("program") Program selectedProgram) {


        // Create a new enrollment record
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setProgram(selectedProgram);
        enrollment.setStartDate(LocalDate.of(2025, 01, 16)); // Set the current date as the start date
        enrollment.setAmountPaid(selectedProgram.getFee());

        // Save the enrollment in the database
        enrollmentRepository.save(enrollment);

        // Return the view with the enrollment confirmation
        return "enrollmentcompleted"; // A view that confirms the enrollment
    }
}


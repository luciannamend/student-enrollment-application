package com.lucianna.mendonca.studentenrollmentapplication.controller;

import com.lucianna.mendonca.studentenrollmentapplication.model.Enrollment;
import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import com.lucianna.mendonca.studentenrollmentapplication.model.Student;
import com.lucianna.mendonca.studentenrollmentapplication.repository.EnrollmentRepository;
import com.lucianna.mendonca.studentenrollmentapplication.repository.ProgramRepository;
import com.lucianna.mendonca.studentenrollmentapplication.repository.StudentRepository;
import com.lucianna.mendonca.studentenrollmentapplication.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller()
public class EnrollmentController {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @PostMapping("/enroll")
    public String enrollInProgram(@RequestParam("programCode") Integer programCode,
                                  @ModelAttribute("student") Student student,
                                  Model model) {
        // Fetch the selected program from the database
        Program selectedProgram = programRepository.findByProgramCode(programCode);

        // Create a new enrollment record
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setProgram(selectedProgram);
        enrollment.setStartDate(LocalDate.now()); // Set the current date as the start date
        enrollment.setAmountPaid(new BigDecimal(100));

        // Save the enrollment in the database
        enrollmentRepository.save(enrollment);

        // Add success message to the model
        model.addAttribute("message", "You have successfully enrolled in " +
                selectedProgram.getProgramName());

        // Return the view with the enrollment confirmation
        return "enrollmentConfirmation"; // A view that confirms the enrollment
    }
}

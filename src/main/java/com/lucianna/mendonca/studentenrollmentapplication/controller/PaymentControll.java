package com.lucianna.mendonca.studentenrollmentapplication.controller;

import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import com.lucianna.mendonca.studentenrollmentapplication.model.Student;
import com.lucianna.mendonca.studentenrollmentapplication.repository.EnrollmentRepository;
import com.lucianna.mendonca.studentenrollmentapplication.repository.ProgramRepository;
import com.lucianna.mendonca.studentenrollmentapplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PaymentControll {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/payment-details")
    public String showPaymentDetails(Model model) {

        System.out.println(model);

        // Check if student and program are available in the model
        if (model.containsAttribute("student") && model.containsAttribute("program")) {

            return "paymentdetail"; // Return the view for payment details

        } else {
            // Handle the case where student or program is missing
            return "redirect:/programs";
        }
    }

    @PostMapping("/process-payment")
    public String processPayment(@RequestParam("programCode") Integer programCode,
                                 @RequestParam("studentId") Long studentId,
                                 RedirectAttributes redirectAttributes){

        Program selectedProgram = programRepository.findByProgramCode(programCode);
        Student currentStudent = studentRepository.findStudentByStudentId(studentId);

        if (selectedProgram == null || currentStudent == null) {
            System.out.println("ERROR GETTING SELECT PROGRAM AND CURRENT STUDENT");
        }

        System.out.println(selectedProgram.getProgramName());
        System.out.println(currentStudent.getUserName());

        redirectAttributes.addFlashAttribute("student", currentStudent);
        redirectAttributes.addFlashAttribute("program", selectedProgram);


        // calls payment details page
        return "redirect:/payment-details";
    }
}

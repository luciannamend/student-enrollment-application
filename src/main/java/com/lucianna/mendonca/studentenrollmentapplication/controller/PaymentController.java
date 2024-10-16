package com.lucianna.mendonca.studentenrollmentapplication.controller;

import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import com.lucianna.mendonca.studentenrollmentapplication.model.Student;
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
public class PaymentController {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private StudentRepository studentRepository;

    // Show payment detail page
    @GetMapping("/payment-details")
    public String showPaymentDetails(Model model) {
        // Check if student and program exist
        if (model.containsAttribute("student")
                && model.containsAttribute("program")) {
            // Go to Payment page
            return "paymentdetail";
        } else {
            // Handle the case where student or program is missing
            return "redirect:/programs";
        }
    }

    // Method to Process Payment
    @PostMapping("/process-payment")
    public String processPayment(@RequestParam("programCode") Integer programCode,
                                 @RequestParam("studentId") Long studentId,
                                 RedirectAttributes redirectAttributes){
        // Get Program and Student obj
        Program selectedProgram = programRepository.findByProgramCode(programCode);
        Student currentStudent = studentRepository.findStudentByStudentId(studentId);
        // if no student
        if (currentStudent == null) {
            // Redirect to index
            return "index";
        }
        if (selectedProgram == null) {
            // maintain in program
            return "program";
        }
        // Redirect objects
        redirectAttributes.addFlashAttribute("student", currentStudent);
        redirectAttributes.addFlashAttribute("program", selectedProgram);
        // redirect to payment method
        return "redirect:/payment-details";
    }
}

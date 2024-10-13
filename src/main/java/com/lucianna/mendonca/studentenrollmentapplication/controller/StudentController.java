package com.lucianna.mendonca.studentenrollmentapplication.controller;

import com.lucianna.mendonca.studentenrollmentapplication.model.Student;
import com.lucianna.mendonca.studentenrollmentapplication.repository.EnrollmentRepository;
import com.lucianna.mendonca.studentenrollmentapplication.repository.ProgramRepository;
import com.lucianna.mendonca.studentenrollmentapplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class StudentController {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Root page calls index.html
    @GetMapping("/")
    public String showForm(Model model){
        return "index";
    }

    @PostMapping("/register")
    public String registerStudent(@ModelAttribute Student student){

        try{
            studentRepository.save(student);
            System.out.println("STUDENT SAVED");
            //todo
            // let student know their registration was successful
            return "index";
        } catch (Exception e) {
            throw new RuntimeException(e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }


}

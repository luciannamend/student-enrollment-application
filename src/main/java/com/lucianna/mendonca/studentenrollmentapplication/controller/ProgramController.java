package com.lucianna.mendonca.studentenrollmentapplication.controller;

import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import com.lucianna.mendonca.studentenrollmentapplication.model.Student;
import com.lucianna.mendonca.studentenrollmentapplication.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller()
public class ProgramController {

    @Autowired
    private ProgramService programService;

    // Display all programs from the database
    @GetMapping("/programs")
    public String showPrograms(@ModelAttribute("student") Student student, Model model) {
        // If student is not found in the session, redirect to the index page
        if (student == null) {
            return "index";
        }
        // Add the student and the list of programs to the model
        model.addAttribute("student", student);
        List<Program> programs = programService.getAllPrograms();
        model.addAttribute("programs", programs);
        return "program"; // Render program.html
    }
}

package com.lucianna.mendonca.studentenrollmentapplication.controller;

import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import com.lucianna.mendonca.studentenrollmentapplication.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller()
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @GetMapping("/programs")
    public String showPrograms(Model model) {

        // Add the student attribute if available
        if (model.asMap().containsKey("student")) {
            model.addAttribute("student", model.asMap().get("student"));
        }else {
            return "index";
        }

        List<Program> programs = programService.getAllPrograms();
        model.addAttribute("programs", programs); // Add programs to the model

        return "program"; // Return the program.html
    }

}

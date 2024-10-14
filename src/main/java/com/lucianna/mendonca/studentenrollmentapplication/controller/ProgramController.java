package com.lucianna.mendonca.studentenrollmentapplication.controller;

import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import com.lucianna.mendonca.studentenrollmentapplication.repository.ProgramRepository;
import com.lucianna.mendonca.studentenrollmentapplication.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller()
public class ProgramController {

    @Autowired
    private ProgramService programService;
    @Autowired
    private ProgramRepository programRepository;

    @GetMapping("/programs")
    public String showPrograms(Model model, RedirectAttributes redirectAttributes) {

        // Add the student attribute if available
        if (model.asMap().containsKey("student")) {
            model.addAttribute("student", model.asMap().get("student"));
        } else {
            return "index";
        }

        List<Program> programs = programService.getAllPrograms();
        model.addAttribute("programs", programs); // Add programs to the model


        // redirect student
        if (model.containsAttribute("student")){
            redirectAttributes.addFlashAttribute("student", model.asMap().get("student") );
        }


        return "program"; // Return the program.html
    }

}

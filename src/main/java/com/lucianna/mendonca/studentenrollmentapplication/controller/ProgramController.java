package com.lucianna.mendonca.studentenrollmentapplication.controller;

import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import com.lucianna.mendonca.studentenrollmentapplication.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller()
public class ProgramController {

    @Autowired
    private ProgramService programService;

    // Display all programs form db
    @GetMapping("/programs")
    public String showPrograms(Model model, RedirectAttributes redirectAttributes) {
        // Add the student attribute if available
        if (model.asMap().containsKey("student")) {
            model.addAttribute("student", model.asMap().get("student"));
        } else {
            // if no student, return to index
            return "index";
        }
        // get all programs from db
        List<Program> programs = programService.getAllPrograms();
        // add programs into model to be displayed on .html
        model.addAttribute("programs", programs);
        // redirect student
        if (model.containsAttribute("student")){
            redirectAttributes.addFlashAttribute("student", model.asMap().get("student") );
        }
        return "program";
    }
}

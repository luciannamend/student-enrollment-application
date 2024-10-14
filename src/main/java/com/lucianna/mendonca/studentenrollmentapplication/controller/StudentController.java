package com.lucianna.mendonca.studentenrollmentapplication.controller;

import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import com.lucianna.mendonca.studentenrollmentapplication.model.Student;
import com.lucianna.mendonca.studentenrollmentapplication.repository.EnrollmentRepository;
import com.lucianna.mendonca.studentenrollmentapplication.repository.ProgramRepository;
import com.lucianna.mendonca.studentenrollmentapplication.repository.StudentRepository;
import com.lucianna.mendonca.studentenrollmentapplication.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProgramService programService;
    private byte[] salt = new byte[6];

    // Root page calls index.html
    @GetMapping("/")
    public String showForm(Model model){
        return "index";
    }

    // Save student
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("student") Student student){

        // Hash password
        System.out.println(student.getPassword());
        String hashedPassword = hashPassword(student.getPassword(), salt);
        student.setPassword(hashedPassword);
        System.out.println(hashedPassword);

        // save student
        try{
            studentRepository.save(student);
            System.out.println("STUDENT SAVED");
            //todo
            // let student know their registration was successful

            return "index"; // go to index.html

        } catch (Exception e) {
            throw new RuntimeException(e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam("login_username") String username, @RequestParam("login_password") String password,
                        RedirectAttributes redirectAttributes) {
        // find user by userName
        Student retrievedStudent = studentRepository.findByUserName(username);

        // Hash the provided password with the salt
        String hashedPassword = hashPassword(password, salt);

        // Compare the hashed password with the stored hashed password
        if (retrievedStudent != null && hashedPassword.equals(retrievedStudent.getPassword())) {
            System.out.println("SUCCESSFUL LOGIN");

            // Add student to be accessed in the program page
            redirectAttributes.addFlashAttribute("student", retrievedStudent);

            return "redirect:/programs";  // Redirect to the programs page
        }

        // if login not success redirect to index.html
        return "index";
    }

    // Hash password
    public static String hashPassword(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            byte[] hash = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}

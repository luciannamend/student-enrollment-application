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
import org.springframework.web.bind.annotation.RequestParam;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Controller
public class StudentController {

    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    private byte[] salt = new byte[6];

    // Root page calls index.html
    @GetMapping("/")
    public String showForm(Model model){
        return "index";
    }

    // Save student
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("student") Student student){

        System.out.println(student.getPassword());
        String hashedPassword = hashPassword(student.getPassword(), salt);
        student.setPassword(hashedPassword);
        System.out.println(hashedPassword);

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

    // Student login
    @PostMapping("/login")
    public String login(@RequestParam("login_username") String username, @RequestParam("login_password") String password,
                        Model model) {
        // find user by userName
        Student retrievedUser = studentRepository.findByUserName(username);

        // Hash the provided password with the salt
        String hashedPassword = hashPassword(password, salt);

        // Compare the hashed password with the stored hashed password
        if (hashedPassword.equals(retrievedUser.getPassword())) {
            System.out.println("SUCCESSFUL LOGIN");
            return "program"; // Redirect to program page
        }
        return "index";
    }

    // Generate a random salt
    public static byte[] generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
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

package com.lucianna.mendonca.studentenrollmentapplication.controller;

import com.lucianna.mendonca.studentenrollmentapplication.model.Student;
import com.lucianna.mendonca.studentenrollmentapplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    private byte[] salt = new byte[6];

    // Root page calls index.html
    @GetMapping("/")
    public String showForm(Model model){
        return "index";
    }

    // Save student on db
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute("student") Student student){

        // Hash the password
        String hashedPassword = hashPassword(student.getPassword(), salt);
        student.setPassword(hashedPassword);

        // save student
        try{
            studentRepository.save(student);
            //todo
            // let student know their registration was successful
            return "index"; // go to index.html
        } catch (Exception e) {
            throw new RuntimeException(e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    // Student Login Authentication
    @PostMapping("/login")
    public String login(@RequestParam("login_username") String username,
                        @RequestParam("login_password") String password,
                        RedirectAttributes redirectAttributes) {
        // Find user by userName
        Student retrievedStudent = studentRepository.findByUserName(username);

        // Hash the provided password with the salt
        String hashedPassword = hashPassword(password, salt);

        // If password exists and is equal to the stored password
        if (retrievedStudent != null && hashedPassword.equals(retrievedStudent.getPassword())) {
            // Redirect student obj
            redirectAttributes.addFlashAttribute("student", retrievedStudent);
            // Redirect to the programs page
            return "redirect:/studentProfile";
        }
        // if login not successful, redirect to index page
        return "index";
    }

    // Go to student profile
    @GetMapping("/studentProfile")
    public String studentProfile(@ModelAttribute("student") Student student, Model model){
        // Add the student object to the model to display on the profile page
        model.addAttribute("student", student);
        return "profile";
    }

    // Displauy edit prof
    @GetMapping("/editProfile")
    public String editStudentProfile(@RequestParam("studentId") Long studentId, Model model) {
        // Find student by ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        // Add the student to the model
        model.addAttribute("student", student);
        return "editProfile";
    }

    @PostMapping("/updateProfile")
    public String updateStudentProfile(@RequestParam("studentId") Long studentId,
                                       @RequestParam("firstName") String firstName,
                                       @RequestParam("lastName") String lastName,
                                       @RequestParam("address") String address,
                                       @RequestParam("city") String city,
                                       @RequestParam("postalCode") String postalCode,
                                       @RequestParam("technicalSkills") String technicalSkills,
                                       @RequestParam(value = "password", required = false) String password,
                                       RedirectAttributes redirectAttributes) {
        // Find student by ID
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Update student fields
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAddress(address);
        student.setCity(city);
        student.setPostalCode(postalCode);
        student.setTechnicalSkills(technicalSkills);

        if (password != null && !password.isEmpty()) {
            String hashedPassword = hashPassword(password, salt);
            student.setPassword(hashedPassword);
        }

        // Save the updated student profile
        studentRepository.save(student);

        // Redirect to the student profile page with updated information
        redirectAttributes.addFlashAttribute("student", student);
        return "redirect:/studentProfile";
    }



    // Method to hash password
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

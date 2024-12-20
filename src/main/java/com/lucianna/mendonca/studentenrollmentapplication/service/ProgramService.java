package com.lucianna.mendonca.studentenrollmentapplication.service;

import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import com.lucianna.mendonca.studentenrollmentapplication.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    @Autowired
    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    // Method do create random programs
    public List<Program> createPrograms(){
        List<Program> programs = new ArrayList<>();
        programs.add(new Program(1010, "Software engineering Technology",
                "6 Semesters", new BigDecimal(3500), "Professor 1"));
        programs.add(new Program(2020, "Game development",
                "6 Semesters", new BigDecimal(2500), "Professor 2"));
        programs.add(new Program(3030, "Graphic Design",
                "6 Semesters", new BigDecimal(3000), "Professor 3"));
        programs.add(new Program(4040, "Mobile Applications Development",
                "2 Semesters", new BigDecimal(3500), "Professor 4"));
        return programs;
    }

    // Method that adds and return all programs of db
    public List<Program> getAllPrograms(){
        // Get programs from db
        List<Program> allPrograms = programRepository.findAll();
        // return programs if not empty
        if(!allPrograms.isEmpty()){
            return allPrograms;
        }
        // save and return programs if db is empty
        return programRepository.saveAll(createPrograms());
    }
}

package com.lucianna.mendonca.studentenrollmentapplication.repository;

import com.lucianna.mendonca.studentenrollmentapplication.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer> {

    Program findByProgramCode(Integer programCode);
}

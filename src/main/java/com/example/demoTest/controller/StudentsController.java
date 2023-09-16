package com.example.demoTest.controller;
import com.example.demoTest.model.entity.Profile;
import com.example.demoTest.model.dto.ResponseDto;
import com.example.demoTest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/students")
public class StudentsController {

    private final StudentService studentService;

    @Autowired
    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/students")
    public ResponseDto createProfile(@Valid @RequestBody Profile profile) throws Exception {
        return studentService.createProfile(profile);
    }

}


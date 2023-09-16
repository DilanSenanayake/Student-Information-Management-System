package com.example.SIMS.controller;
import com.example.SIMS.model.entity.Profile;
import com.example.SIMS.model.dto.ResponseDto;
import com.example.SIMS.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1")
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

    @GetMapping(value = "/students")
    public ResponseDto getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/students/{studentId}")
    public ResponseDto getStudentById(@PathVariable String studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping(value = "/students/{studentId}")
    public ResponseDto updateProfile(@PathVariable String studentId,
                                     @RequestBody Profile profile) throws Exception {
        return studentService.updateProfile(studentId, profile);
    }

}


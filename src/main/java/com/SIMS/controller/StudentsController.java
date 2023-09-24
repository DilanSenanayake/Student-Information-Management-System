package com.SIMS.controller;
import com.SIMS.model.entity.Profile;
import com.SIMS.service.StudentService;
import com.SIMS.model.dto.ResponseDto;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StudentsController {

    private final StudentService studentService;

    @Autowired
    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/students")
    public ResponseDto<Profile> createProfile(@Valid @RequestBody Profile profile) throws Exception {
        return studentService.createProfile(profile);
    }

    @GetMapping(value = "/students")
    public ResponseDto<List<String>> getAllStudents() throws Exception {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/students/{studentId}")
    public ResponseDto<Profile> getStudentById(@PathVariable String studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping(value = "/students/{studentId}")
    public ResponseDto<Profile> updateProfile(@PathVariable String studentId,
                                     @RequestBody Profile profile) throws Exception {
        return studentService.updateProfile(studentId, profile);
    }

    @DeleteMapping(value = "/students/{studentId}")
    public ResponseDto<DeleteResult> deleteStudent(@PathVariable String studentId) throws Exception {
        return studentService.deleteStudent(studentId);
    }

}


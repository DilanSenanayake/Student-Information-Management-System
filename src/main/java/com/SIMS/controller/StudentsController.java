package com.SIMS.controller;
import com.SIMS.model.entity.Profile;
import com.SIMS.service.StudentService;
import com.SIMS.model.dto.ResponseDto;
import com.mongodb.client.result.DeleteResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("api/v1")
@Tag(name = "Student", description = "Endpoint for students")
public class StudentsController {

    private final StudentService studentService;

    @Autowired
    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/students")
    @Operation(summary = "Add a student", description = "Add a student")
    public ResponseDto<Profile> createProfile(@Valid @RequestBody Profile profile) throws Exception {
        return studentService.createProfile(profile);
    }

    @GetMapping(value = "/students")
    @Operation(summary = "Get all student", description = "Get all student")
    public ResponseDto<List<String>> getAllStudents() throws Exception {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/students/{studentId}")
    @Operation(summary = "Get student by id", description = "Get student by id")
    public ResponseDto<Profile> getStudentById(@PathVariable String studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping(value = "/students/{studentId}")
    @Operation(summary = "Edit student details", description = "Edit student details")
    public ResponseDto<Profile> updateProfile(@PathVariable String studentId,
                                     @RequestBody Profile profile) throws Exception {
        return studentService.updateProfile(studentId, profile);
    }

    @DeleteMapping(value = "/students/{studentId}")
    @Operation(summary = "Delete a student", description = "Delete a student")
    public ResponseDto<DeleteResult> deleteStudent(@PathVariable String studentId) throws Exception {
        return studentService.deleteStudent(studentId);
    }

}


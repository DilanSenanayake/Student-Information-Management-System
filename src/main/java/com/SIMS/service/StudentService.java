package com.SIMS.service;

import com.SIMS.model.entity.Profile;
import com.SIMS.model.dto.ResponseDto;
import com.SIMS.repository.StudentRepository;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.UUID.randomUUID;

@Service
public class StudentService {
    StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseDto createProfile(Profile profile) throws Exception {
        profile.setStudentId(randomUUID().toString());
        Profile createdProfile;
        Profile existingProfile = studentRepository.getProfileByEmail(profile.getEmail());

        // Check existing profile for given email address
        if (existingProfile != null) {
            return new ResponseDto(HttpStatus.OK.toString(),"Profile already exists", null);
        } else {
            createdProfile = studentRepository.saveProfile(profile);

            if (createdProfile != null) {
                return new ResponseDto(HttpStatus.CREATED.toString(),"Profile created successfully", createdProfile);
            } else {
                return new ResponseDto(HttpStatus.CREATED.toString(),"Profile creation failed", null);
            }
        }

    }

    public ResponseDto getAllStudents() {
        List<String> students = studentRepository.getAllStudents();
        if (students != null) {
            return new ResponseDto(HttpStatus.OK.toString(),"All students found", students);
        } else {
            return new ResponseDto(HttpStatus.OK.toString(),"All students not found", null);
        }
    }

    public ResponseDto getStudentById(String studentId) {
        Profile profile = studentRepository.getStudentById(studentId);
        if (profile != null) {
            return new ResponseDto(HttpStatus.OK.toString(),"student found", profile);
        } else {
            return new ResponseDto(HttpStatus.OK.toString(),"student not found", null);
        }
    }

    public ResponseDto updateProfile(String studentId, Profile profile) throws Exception {
        profile.setStudentId(studentId);
        Profile updatedProfile = studentRepository.updateProfile(profile);
        if (updatedProfile != null) {
            return new ResponseDto(HttpStatus.OK.toString(),"Student updated", updatedProfile);
        } else {
            return new ResponseDto(HttpStatus.OK.toString(),"Student not updated", null);
        }
    }

    public ResponseDto deleteStudent(String studentId) throws Exception {
        DeleteResult deleteResult = studentRepository.deleteStudent(studentId);
        if (deleteResult != null) {
            return new ResponseDto(HttpStatus.OK.toString(),"Student deleted", deleteResult);
        } else {
            return new ResponseDto(HttpStatus.OK.toString(),"Student not deleted", null);
        }
    }
}

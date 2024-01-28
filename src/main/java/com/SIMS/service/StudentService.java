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

    public ResponseDto<Profile> createProfile(Profile profile) {
        profile.setStudentId(randomUUID().toString());
        Profile createdProfile;
        Profile existingProfile = studentRepository.getProfileByEmail(profile.getEmail());

        // Check existing profile for given email address
        if (existingProfile != null) {
            return new ResponseDto<>(HttpStatus.OK.toString(),"Profile already exists", null);
        } else {
            createdProfile = studentRepository.saveProfile(profile);

            if (createdProfile != null) {
                return new ResponseDto<>(HttpStatus.CREATED.toString(),"Profile created successfully", createdProfile);
            }
            return new ResponseDto<>(HttpStatus.CREATED.toString(),"Profile creation failed", null);
        }

    }

    public ResponseDto<List<Profile>> getAllStudents() throws Exception {
        List<Profile> students = studentRepository.getAllStudents();
        if (students != null) {
            return new ResponseDto<>(HttpStatus.OK.toString(),"All students found", students);
        }
        return new ResponseDto<>(HttpStatus.OK.toString(),"All students not found", null);
    }

    public ResponseDto<Profile> getStudentById(String studentId) {
        Profile profile = studentRepository.getStudentById(studentId);
        if (profile != null) {
            return new ResponseDto<>(HttpStatus.OK.toString(),"student found", profile);
        }
        return new ResponseDto<>(HttpStatus.OK.toString(),"student not found", null);
    }

    public ResponseDto<Profile> updateProfile(String studentId, Profile profile) throws Exception {
        profile.setStudentId(studentId);
        Profile updatedProfile = studentRepository.updateProfile(profile);
        if (updatedProfile != null) {
            return new ResponseDto<>(HttpStatus.OK.toString(),"Student updated", updatedProfile);
        }
        return new ResponseDto<>(HttpStatus.OK.toString(),"Student not updated", null);
    }

    public ResponseDto<DeleteResult> deleteStudent(String studentId) throws Exception {
        DeleteResult deleteResult = studentRepository.deleteStudent(studentId);
        if (deleteResult != null) {
            return new ResponseDto<>(HttpStatus.OK.toString(),"Student deleted", deleteResult);
        }
        return new ResponseDto<>(HttpStatus.OK.toString(),"Student not deleted", null);
    }
}

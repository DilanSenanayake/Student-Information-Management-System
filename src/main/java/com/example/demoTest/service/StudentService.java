package com.example.demoTest.service;

import com.example.demoTest.model.entity.Profile;
import com.example.demoTest.model.dto.ResponseDto;
import com.example.demoTest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
        Profile createProfile;
        Profile existingProfile = studentRepository.getProfileByEmail(profile.getEmail());

        // Check existing profile for given email address
        if (existingProfile != null) {
            return new ResponseDto(HttpStatus.OK.toString(),"Profile already exists", null);
        } else {
            createProfile = studentRepository.saveProfile(profile);

            if (createProfile != null) {
                return new ResponseDto(HttpStatus.CREATED.toString(),"Profile created successfully", null);
            } else {
                throw new Exception("Profile creation failed");
            }
        }

    }
}

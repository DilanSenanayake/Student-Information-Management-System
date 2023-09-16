package com.example.demoTest.service;

import com.example.demoTest.model.entity.Profile;
import com.example.demoTest.model.dto.ResponseDto;
import com.example.demoTest.repository.studentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Service
public class StudentService {
    studentRepository studentRepository;

    public ResponseDto createProfile(Profile profile) throws Exception {
        profile.setStudentId(randomUUID().toString());
        Profile createProfile;

        createProfile = studentRepository.saveProfile(profile);

        return new ResponseDto(HttpStatus.CREATED.toString(),"Profile created successfully", null);
    }
}
